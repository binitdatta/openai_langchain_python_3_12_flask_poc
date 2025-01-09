# What this App do

## This is a Python 3.12 and Flask CRUD REST API with a Local MySQL Backend DB

## How to Run this:

``` 
- 1. Install or make sure we have MySQL 8.x
- 2. Install we have Python 3.12
- 3. Install a MySQL UI Client such as Wokrbench or DataGrip
- 4. Create a new Connection in the MySQL UI Client 
- 5. Create a new Database Schema called python_flask_orders
- 6. create database python_flask_orders
- 7. use python_flask_orders
- 8. Run the following ddl

create table python_flask_orders.orders
(
    id           int auto_increment
        primary key,
    order_no     varchar(50)    not null,
    order_date   datetime       not null,
    product_id   varchar(50)    not null,
    qty_ordered  int            not null,
    unit_price   decimal(10, 2) not null,
    customer_id  varchar(50)    not null,
    order_status varchar(20)    not null
);

9. Insert some seed data:

1,ORD1001,2024-12-25 10:30:00,PROD001,2,50.00,CUST001,Shipped
2,ORD1002,2024-12-26 11:00:00,PROD002,1,150.00,CUST002,Processing
3,ORD1003,2024-12-27 12:15:00,PROD003,5,20.00,CUST003,Delivered
4,ORD1004,2024-12-28 13:45:00,PROD004,3,75.00,CUST004,Cancelled
5,ORD1005,2024-12-29 14:30:00,PROD005,4,120.00,CUST005,Shipped

- 10. Install Pycharm IDE
- 11. Clone this repository and open in PyCharm
- 12. Open a new terminal
- 13. Run 
```



To run a Python Flask application with a `Pipfile` inside PyCharm, follow these steps:

---

### 1. **Clone the GitHub Repository**
- Open a terminal or Git Bash and clone the GitHub repository to your local machine:
  ```bash
  git clone <repository_url>
  ```
- Replace `<repository_url>` with the actual URL of the GitHub repository.

---

### 2. **Open the Project in PyCharm**
- Launch PyCharm.
- From the **File** menu, select **Open** and navigate to the directory of the cloned repository.
- Open the project folder containing the `Pipfile`.

---

### 3. **Configure the Python Interpreter**
PyCharm supports `pipenv`, the tool used to manage dependencies with a `Pipfile`.

1. **Add a New Python Interpreter:**
   - Go to **File** > **Settings** (or **Preferences** on macOS) > **Project: [Project Name]** > **Python Interpreter**.
   - Click the **gear icon** and select **Add...**.
   - Choose **Pipenv Environment**.

2. **Configure Pipenv:**
   - In the "Environment" settings, ensure **Existing Pipfile** is selected.
   - PyCharm will automatically detect the `Pipfile` in the project directory.
   - Choose an interpreter path for Python 3.12 (if not already installed, install Python 3.12).
   - Click **OK** to save.

3. **Verify the Interpreter:**
   - After configuration, PyCharm will install the dependencies specified in the `Pipfile`.

---

### 4. **Set the Flask Application Configuration**
1. **Specify the Flask App:**
   - Create a `.flaskenv` or `app.py` file if not already present, and ensure it contains the following:
     ```bash
     FLASK_APP=app.py
     FLASK_ENV=development
     ```

2. **Alternatively, set it in PyCharm:**
   - Go to **Run** > **Edit Configurations**.
   - Click **+ Add New Configuration** and select **Flask**.
   - In the **Script path**, point to the main entry file (e.g., `app.py`).
   - In the **Environment variables**, add:
     ```
     FLASK_APP=app.py
     FLASK_ENV=development
     ```

---

### 5. **Install Dependencies**
If PyCharm doesn't automatically install dependencies, run:
```bash
pipenv install
```
This will install all dependencies specified in the `Pipfile`.

---

### 6. **Run the Flask Application**
1. **Using PyCharm's Run Button:**
   - Click the green **Run** button in PyCharm or press `Shift + F10` to run the Flask application.

2. **Using Terminal:**
   - Open the terminal in PyCharm and activate the `pipenv` environment:
     ```bash
     pipenv shell
     ```
   - Run the Flask app:
     ```bash
     python3 app.py
     ```

---

### 7. **Access the Application**
- Open your browser and go to `http://127.0.0.1:5000/` to view the Flask application.

---

### Notes:
- Ensure the `app.py` file or the main application file exists and has a valid Flask application object defined (e.g., `app = Flask(__name__)`).
- If using `flask-sqlalchemy` with `pymysql`, ensure the `SQLALCHEMY_DATABASE_URI` is properly configured in the app, e.g.:
  ```python
  app.config['SQLALCHEMY_DATABASE_URI'] = 'mysql+pymysql://user:password@localhost/dbname'
  ```

Would you like detailed steps on setting up the Flask-SQLAlchemy database or using `pipenv`?

