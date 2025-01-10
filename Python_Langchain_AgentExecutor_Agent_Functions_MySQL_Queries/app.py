from flask import Flask, request, jsonify, g, render_template
import mysql.connector
from flask_cors import CORS

from langchain.chat_models import ChatOpenAI
from langchain.prompts import (
    ChatPromptTemplate,
    HumanMessagePromptTemplate,
    MessagesPlaceholder
)
from langchain.schema import SystemMessage
from langchain.agents import OpenAIFunctionsAgent, AgentExecutor
from langchain.memory import ConversationBufferMemory
from dotenv import load_dotenv
from tools.sql_1 import run_query_tool, list_tables, describe_tables_tool
from tools.report_4 import write_report_tool
from handlers.chat_model_start_handler import ChatModelStartHandler

# Load environment variables
load_dotenv()
# Initialize Flask app
app = Flask(__name__)
CORS(app)

handler = ChatModelStartHandler()

# llm = OpenAI(callbacks=[handler], model_name="gpt-4")
# Define LangChain components
chat = ChatOpenAI(callbacks=[handler], model_name="gpt-4")

# Retrieve database tables
tables = list_tables()

# Define the prompt
prompt = ChatPromptTemplate(
    messages=[
        SystemMessage(content=(
            "You are an AI that has access to a MySQL database.\n"
            f"The database has tables of: {tables}\n"
            "Do not make any assumptions about what tables exist "
            "Always consider this table ticket_table_demo for all queries"
            "Use the date_created column using the YYYY-MM-DD format to answer date related questions"
            "or what columns exist. Instead, use the 'describe_tables' function"
        )),
        MessagesPlaceholder(variable_name="chat_history"),
        HumanMessagePromptTemplate.from_template("{input}"),
        MessagesPlaceholder(variable_name="agent_scratchpad")
    ]
)

# Define memory and tools
memory = ConversationBufferMemory(memory_key="chat_history", return_messages=True)
tools = [run_query_tool, describe_tables_tool, write_report_tool]

# Define agent and executor
agent = OpenAIFunctionsAgent(
    llm=chat,
    prompt=prompt,
    tools=tools
)
agent_executor = AgentExecutor(
    agent=agent,
    tools=tools,
    memory=memory
)

@app.route("/")
def home():
    return render_template("chat.html")

# Flask route for the chatbot
@app.route("/chat", methods=["POST"])
def chat_endpoint():
    try:
        # Parse user input from the request
        user_input = request.json.get("input", "")

        if not user_input:
            return jsonify({"error": "No input provided"}), 400

        # Process the input using the agent_executor
        response = agent_executor.run(user_input)
        print(jsonify({"response": response}))
        return jsonify({"response": response})
    except Exception as e:
        return jsonify({"error": str(e)}), 500

if __name__ == "__main__":
    app.run(host="0.0.0.0", port=5001, debug=True)
