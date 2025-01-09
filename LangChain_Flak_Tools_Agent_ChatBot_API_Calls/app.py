from flask import Flask, jsonify, request, render_template
from langchain import PromptTemplate, OpenAI
import requests
import re
from handlers.chat_model_start_handler import ChatModelStartHandler

app = Flask(__name__)

# Initialize LangChain model (using OpenAI as an example)
handler = ChatModelStartHandler()

llm = OpenAI(callbacks=[handler], model_name="gpt-4")


def get_order_status(order_id):
    api_url = f"http://localhost:5004/orders/{order_id}"  # Replace with your API URL
    headers = {
        "Authorization": "Bearer YOUR_API_KEY",  # Add API Key if needed
        "Content-Type": "application/json"
    }

    response = requests.get(api_url, headers=headers)
    if response.status_code == 200:
        return response.json()  # Return JSON response
    else:
        return {"error": f"Unable to fetch order status. Status code: {response.status_code}"}

order_status_template = """
You are a helpful chatbot. A user asked about the status of an order. Here is the order information:

Order ID: {order_id}
Order Status JSON: {order_json}

Answer the user's question based on the order status.
"""

prompt = PromptTemplate(
    input_variables=["order_id", "order_json"],
    template=order_status_template
)


@app.route('/chat', methods=['POST'])
def chat():
    data = request.json
    user_message = data.get("message", "")
    order_id = extract_order_id(user_message)  # Implement logic to extract order ID from user input

    # Fetch order status from API
    order_json = get_order_status(order_id)

    return_as_json = request.args.get("format", "json") == "json"

    if "error" in order_json:
        return jsonify({"response": order_json["error"]})

    # Format JSON response for LangChain
    order_json_str = str(order_json)

    # Generate chatbot response
    langchain_prompt = prompt.format(order_id=order_id, order_json=order_json_str)
    chatbot_response = llm(langchain_prompt)

    return jsonify({"response": chatbot_response})


def extract_order_id(user_message):
    # Example: Extract order ID (assuming it's a numeric value in the message)
    match = re.search(r'\b\d+\b', user_message)
    return match.group(0) if match else None

# Serve the HTML file from the templates directory
@app.route('/', methods=['GET'])
def serve_html():
    return render_template('index.html')

if __name__ == '__main__':
    app.run(debug=True, port=5005)