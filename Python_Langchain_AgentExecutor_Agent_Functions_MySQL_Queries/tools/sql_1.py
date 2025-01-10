import mysql.connector
from pydantic.v1 import BaseModel
from typing import List
from langchain.tools import Tool
from dotenv import load_dotenv
import os

def get_connection():
    """Establish connection to the MySQL database."""
    return mysql.connector.connect(
        host="localhost",
        database="keycloak_spicey_jiras",
        user=os.getenv('DB_USERNAME'),
        password=os.getenv('DB_PASSWORD')
    )


def list_tables():
    """List all tables in the database."""
    conn = get_connection()
    try:
        cursor = conn.cursor()
        cursor.execute("SHOW TABLES;")
        rows = cursor.fetchall()
        return "\n".join(row[0] for row in rows if row[0] is not None)
    finally:
        conn.close()


def run_mysql_query(query):
    """Run a MySQL query and return the results."""
    conn = get_connection()
    print(query)
    try:
        cursor = conn.cursor()
        cursor.execute(query)
        return cursor.fetchall()
    except mysql.connector.Error as err:
        return f"The following error occurred: {str(err)}"
    finally:
        conn.close()


class RunQueryArgsSchema(BaseModel):
    query: str


run_query_tool = Tool.from_function(
    name="run_mysql_query",
    description="Run a MySQL query.",
    func=run_mysql_query,
    args_schema=RunQueryArgsSchema
)


def describe_tables(table_names):
    """Describe the schema of the specified tables."""
    conn = get_connection()
    try:
        cursor = conn.cursor()
        tables = ', '.join(f"'{table}'" for table in table_names)
        cursor.execute(f"SHOW CREATE TABLE {', '.join(table_names)};")
        rows = cursor.fetchall()
        return '\n'.join(row[1] for row in rows if len(row) > 1)
    except mysql.connector.Error as err:
        return f"The following error occurred: {str(err)}"
    finally:
        conn.close()


class DescribeTablesArgsSchema(BaseModel):
    tables_names: List[str]


describe_tables_tool = Tool.from_function(
    name="describe_tables",
    description="Given a list of table names, returns the schema of those tables.",
    func=describe_tables,
    args_schema=DescribeTablesArgsSchema
)

# Example usage of the tools
if __name__ == "__main__":
    # List all tables
    print("Tables in the database:")
    print(list_tables())

    # Run a query
    print("\nRunning a sample query:")
    print(run_mysql_query("SELECT COUNT(*) FROM ticket_table_final WHERE status = 'Open';"))

    # Run a query
    print("\nRunning a sample Closed status query:")
    print(run_mysql_query("SELECT COUNT(*) FROM ticket_table_final WHERE status = 'Closed';"))

    # Run a query
    print("\nRunning a sample Ticket query:")
    print(run_mysql_query("SELECT * FROM ticket_table_final WHERE ticket = 'CP-23086';"))

    # Describe a table
    print("\nDescribing table schema:")
    print(describe_tables(["ticket_table_final"]))
