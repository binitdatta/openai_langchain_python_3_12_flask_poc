# What this App do

Here is a step-by-step explanation of the processing flow when the user enters a question in the form and clicks the "Send" button:

---

### 1. **User Input Submission**
   - The user enters a question in the form and clicks the "Send" button.
   - The front-end form (e.g., `chat.html`) sends a POST request to the `/chat` endpoint with the user's input in the JSON payload.

---

### 2. **Flask Route `/chat` Handles the Request**
   - The `chat_endpoint()` function is triggered by the POST request.
   - Inside the function:
     - The user's input is extracted from the request using `request.json.get("input", "")`.
     - If the input is missing or empty, a `400` error response with an appropriate message is returned.

---

### 3. **AgentExecutor Processes the Input**
   - If the input is valid, the `AgentExecutor` object (`agent_executor`) is called with the user's input using `agent_executor.run(user_input)`.
   - This triggers the following chain of events within the LangChain framework:

---

#### 3.1 **AgentExecutor Executes the Agent**
   - The `AgentExecutor` acts as the central controller:
     - It forwards the user's input to the LangChain Agent (`OpenAIFunctionsAgent`).
     - It maintains the conversation history (`ConversationBufferMemory`) by updating the `chat_history` variable.

---

#### 3.2 **Prompt Creation**
   - The `OpenAIFunctionsAgent` uses the `ChatPromptTemplate` to construct a prompt based on:
     - **SystemMessage**: Sets the context and rules for the AI (e.g., database access, table names, date format).
     - **MessagesPlaceholder**: Includes the `chat_history` and `agent_scratchpad` (for intermediary reasoning).
     - **HumanMessagePromptTemplate**: Incorporates the user's input (`{input}`).

---

#### 3.3 **Interaction with the ChatOpenAI API**
   - The `chat` object (`ChatOpenAI`) is invoked with the constructed prompt.
   - This object communicates with the OpenAI GPT-4 model via API and sends:
     - The system message.
     - Conversation history.
     - The user's question (formatted into the prompt template).

---

#### 3.4 **Using Tools for Specific Tasks**
   - If the GPT-4 model's reasoning requires additional steps (e.g., database queries or table descriptions), the `OpenAIFunctionsAgent` calls one of the registered tools:
     - **`run_query_tool`**: Executes SQL queries on the MySQL database.
     - **`describe_tables_tool`**: Describes the schema of the database tables.
     - **`write_report_tool`**: Generates reports based on the query results.
   - The agent integrates these tool outputs into its reasoning and formulates a response.

---

#### 3.5 **Returning the Final Response**
   - The GPT-4 model produces the final response.
   - This response is returned to the `AgentExecutor`.
   - The `AgentExecutor` updates the `ConversationBufferMemory` with the new interaction and forwards the response back to the Flask route.

---

### 4. **Flask Returns the Response**
   - The response from `agent_executor.run()` is encapsulated into a JSON object.
   - A response like `{"response": "AI-generated answer"}` is sent back to the front-end.

---

### 5. **Displaying the Response**
   - The front-end (e.g., `chat.html`) receives the JSON response.
   - The chatbot interface displays the AI-generated answer to the user.

---

### Summary of Key Components
1. **Front-end**:
   - Sends the user's input to the `/chat` endpoint via POST.
2. **Flask Back-end**:
   - Handles input validation and orchestrates LangChain processing.
3. **LangChain Agent & Executor**:
   - Builds prompts, manages memory, and utilizes tools for database queries.
4. **ChatOpenAI API**:
   - Interacts with the GPT-4 model to generate intelligent responses.
5. **Tools**:
   - Perform specific operations (e.g., SQL queries, schema descriptions).
6. **Memory**:
   - Tracks the conversation history for context.

This flow ensures a seamless user experience while leveraging the power of LangChain and OpenAI GPT-4 for dynamic and context-aware chatbot interactions.

![Alt Text](./images/1.png)


![Alt Text](./images/2.png)

![Alt Text](./images/3.png)

![Alt Text](./images/4.png)

![Alt Text](./images/5.png)

![Alt Text](./images/6.png)

![Alt Text](./images/11.png)

![Alt Text](./images/12.png)

![Alt Text](./images/13.png)

![Alt Text](./images/14.png)

![Alt Text](./images/15.png)

![Alt Text](./images/16.png)

![Alt Text](./images/17.png)

![Alt Text](./images/18.png)


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
- 9. Install Pycharm IDE
- 10. Clone this repository and open in PyCharm
- 11. Open a new terminal
- 12. Run 
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

## MySQL commands

```sql 

create keycloak_spicey_jiras

use keycloak_spicey_jiras

create table ticket_table_demo
(
    id             int auto_increment
        primary key,
    ticket         varchar(255)  null,
    status         varchar(255)  null,
    title          varchar(3000) null,
    classification varchar(255)  null,
    date_created   date          null,
    priority       varchar(255)  null,
    remarks        varchar(3000) null
);


INSERT INTO ticket_table_demo (ticket, status, title, classification, date_created, priority, remarks)
VALUES
('TICKET-001', 'Open', 'Fix Keycloak authentication issue', 'Bug', '2025-01-01', 'High', 'Priority fix for production system'),
('TICKET-002', 'In Progress', 'Implement new user onboarding feature', 'Feature', '2025-01-02', 'Medium', 'Work in progress'),
('TICKET-003', 'Closed', 'Update Keycloak version to 21.1', 'Enhancement', '2024-12-15', 'Low', 'Version update completed successfully'),
('TICKET-004', 'Open', 'Investigate performance issue in Keycloak', 'Bug', '2025-01-03', 'Critical', 'Performance degradation in login module'),
('TICKET-005', 'Resolved', 'Add SSO support for external vendors', 'Feature', '2024-12-20', 'High', 'SSO integration completed'),
('TICKET-006', 'Closed', 'Document Keycloak REST API endpoints', 'Task', '2024-12-22', 'Medium', 'Documentation uploaded to Confluence'),
('TICKET-007', 'In Progress', 'Integrate Keycloak with LDAP', 'Enhancement', '2025-01-04', 'High', 'Initial setup completed, testing ongoing'),
('TICKET-008', 'Open', 'Fix role mapping issue in Keycloak', 'Bug', '2025-01-05', 'High', 'Role mapping fails for specific users'),
('TICKET-009', 'Closed', 'Implement audit logging in Keycloak', 'Feature', '2024-12-18', 'Low', 'Audit logging deployed successfully'),
('TICKET-010', 'Resolved', 'Update Keycloak themes for UI consistency', 'Enhancement', '2025-01-01', 'Medium', 'UI themes updated with corporate branding'),
('TICKET-011', 'Open', 'Fix session timeout issue in Keycloak', 'Bug', '2025-01-06', 'Critical', 'Users are logged out prematurely'),
('TICKET-012', 'In Progress', 'Add support for custom authenticators in Keycloak', 'Feature', '2025-01-07', 'High', 'Custom authenticator under development'),
('TICKET-013', 'Closed', 'Update Keycloak user storage provider', 'Enhancement', '2024-12-28', 'Low', 'User storage provider enhancements deployed'),
('TICKET-014', 'Resolved', 'Fix Keycloak database migration scripts', 'Bug', '2025-01-02', 'High', 'Migration scripts tested and deployed'),
('TICKET-015', 'Open', 'Enhance Keycloak admin UI usability', 'Feature', '2025-01-03', 'Medium', 'Feedback gathered for UI improvement'),
('TICKET-016', 'Closed', 'Fix broken links in Keycloak documentation', 'Task', '2024-12-25', 'Low', 'All links verified and fixed'),
('TICKET-017', 'In Progress', 'Implement multi-factor authentication in Keycloak', 'Feature', '2025-01-05', 'Critical', 'MFA feature in testing phase'),
('TICKET-018', 'Resolved', 'Resolve Keycloak cluster synchronization issue', 'Bug', '2024-12-30', 'High', 'Cluster synchronization issue resolved'),
('TICKET-019', 'Open', 'Implement Keycloak health check endpoints', 'Enhancement', '2025-01-07', 'Medium', 'Pending review from security team'),
('TICKET-020', 'In Progress', 'Optimize Keycloak database queries', 'Task', '2025-01-04', 'High', 'Query optimization in progress');

INSERT INTO ticket_table_demo (ticket, status, title, classification, date_created, priority, remarks)
VALUES
('TICKET-021', 'Open', 'Fix Keycloak token expiration issue', 'Bug', '2025-01-08', 'High', 'Tokens expire before configured time'),
('TICKET-022', 'Closed', 'Update Keycloak password policies', 'Enhancement', '2024-12-15', 'Medium', 'Password policies updated with new rules'),
('TICKET-023', 'In Progress', 'Add OAuth2 client for mobile app', 'Feature', '2025-01-06', 'Critical', 'OAuth2 client partially implemented'),
('TICKET-024', 'Open', 'Resolve Keycloak memory leak in production', 'Bug', '2025-01-07', 'Critical', 'Memory usage spikes under load'),
('TICKET-025', 'Resolved', 'Fix email notification errors in Keycloak', 'Bug', '2024-12-31', 'High', 'Email notifications working as expected'),
('TICKET-026', 'Closed', 'Upgrade Keycloak database schema', 'Task', '2024-12-25', 'Low', 'Database schema successfully upgraded'),
('TICKET-027', 'In Progress', 'Integrate Keycloak with external API', 'Feature', '2025-01-08', 'Medium', 'API integration in progress'),
('TICKET-028', 'Open', 'Fix Keycloak admin console login issue', 'Bug', '2025-01-08', 'High', 'Admins unable to log in intermittently'),
('TICKET-029', 'Resolved', 'Enable Keycloak rate limiting', 'Feature', '2024-12-29', 'Medium', 'Rate limiting enabled for login endpoints'),
('TICKET-030', 'Closed', 'Optimize Keycloak startup time', 'Enhancement', '2024-12-28', 'Low', 'Startup time reduced by 30%'),
('TICKET-031', 'Open', 'Fix cross-site scripting vulnerability in Keycloak', 'Bug', '2025-01-08', 'Critical', 'XSS vulnerability detected in user profiles'),
('TICKET-032', 'In Progress', 'Implement Keycloak custom login page', 'Feature', '2025-01-09', 'High', 'Customizations 50% complete'),
('TICKET-033', 'Closed', 'Migrate Keycloak to new cloud provider', 'Task', '2024-12-26', 'High', 'Migration completed successfully'),
('TICKET-034', 'Resolved', 'Fix Keycloak group permissions issue', 'Bug', '2025-01-01', 'High', 'Permissions issue resolved for all groups'),
('TICKET-035', 'Open', 'Investigate Keycloak session persistence issue', 'Bug', '2025-01-09', 'Medium', 'Sessions not persisting after logout'),
('TICKET-036', 'Closed', 'Update Keycloak API documentation', 'Task', '2024-12-20', 'Low', 'API documentation updated with latest changes'),
('TICKET-037', 'In Progress', 'Add Keycloak support for custom identity provider', 'Feature', '2025-01-08', 'High', 'Identity provider integration in progress'),
('TICKET-038', 'Open', 'Fix Keycloak password reset issue', 'Bug', '2025-01-09', 'High', 'Password reset emails not sent'),
('TICKET-039', 'Resolved', 'Enable Keycloak logging for audit trails', 'Enhancement', '2024-12-22', 'Medium', 'Audit trails logging enabled'),
('TICKET-040', 'Closed', 'Optimize Keycloak API response times', 'Task', '2024-12-23', 'Low', 'API response times reduced by 20%'),
('TICKET-041', 'In Progress', 'Add Keycloak support for SAML authentication', 'Feature', '2025-01-08', 'Critical', 'SAML support implementation ongoing'),
('TICKET-042', 'Open', 'Resolve Keycloak DNS configuration issues', 'Bug', '2025-01-09', 'Medium', 'DNS misconfiguration in staging environment'),
('TICKET-043', 'Closed', 'Fix Keycloak admin audit log retention issue', 'Bug', '2024-12-24', 'Low', 'Retention policy updated successfully'),
('TICKET-044', 'Resolved', 'Implement user profile attributes in Keycloak', 'Feature', '2024-12-27', 'High', 'User attributes added for profile customization'),
('TICKET-045', 'Open', 'Fix OAuth2 PKCE validation issue in Keycloak', 'Bug', '2025-01-08', 'Critical', 'PKCE validation fails for specific flows'),
('TICKET-046', 'In Progress', 'Add Keycloak theme for dark mode', 'Enhancement', '2025-01-09', 'Medium', 'Dark mode theme 70% complete'),
('TICKET-047', 'Closed', 'Update Keycloak admin roles', 'Task', '2024-12-28', 'Low', 'Admin roles updated with new permissions'),
('TICKET-048', 'Resolved', 'Fix Keycloak token signature issue', 'Bug', '2024-12-30', 'High', 'Token signature verified successfully'),
('TICKET-049', 'Open', 'Investigate Keycloak 404 errors on login', 'Bug', '2025-01-09', 'High', 'Login intermittently results in 404 errors'),
('TICKET-050', 'In Progress', 'Implement Keycloak multi-tenant support', 'Feature', '2025-01-09', 'Critical', 'Multi-tenancy implementation ongoing'),
('TICKET-051', 'Closed', 'Fix Keycloak federation provider bugs', 'Bug', '2024-12-25', 'Low', 'Federation provider issues resolved'),
('TICKET-052', 'Resolved', 'Update Keycloak client configurations', 'Task', '2024-12-27', 'Medium', 'Client configurations updated for all environments'),
('TICKET-053', 'Open', 'Fix Keycloak CORS policy issues', 'Bug', '2025-01-09', 'High', 'CORS policy blocking frontend requests'),
('TICKET-054', 'In Progress', 'Add Keycloak support for user consent flows', 'Feature', '2025-01-08', 'Medium', 'Consent flow design completed'),
('TICKET-055', 'Closed', 'Optimize Keycloak session storage', 'Enhancement', '2024-12-29', 'Low', 'Session storage performance improved'),
('TICKET-056', 'Resolved', 'Fix Keycloak resource server configuration', 'Bug', '2024-12-31', 'High', 'Resource server successfully configured'),
('TICKET-057', 'Open', 'Fix Keycloak duplicate user issue', 'Bug', '2025-01-09', 'Critical', 'Duplicate users created during bulk imports'),
('TICKET-058', 'In Progress', 'Implement Keycloak login analytics', 'Feature', '2025-01-08', 'High', 'Analytics tracking login events in progress'),
('TICKET-059', 'Resolved', 'Enable Keycloak health monitoring alerts', 'Enhancement', '2024-12-30', 'Medium', 'Alerts configured for health checks'),
('TICKET-060', 'Closed', 'Fix Keycloak session revocation delays', 'Bug', '2024-12-25', 'Low', 'Session revocation working as expected');


INSERT INTO ticket_table_demo (ticket, status, title, classification, date_created, priority, remarks)
VALUES
('TICKET-061', 'Open', 'Investigate Keycloak CPU spike during load tests', 'Bug', '2025-01-09', 'Critical', 'CPU usage reaches 100% under heavy load'),
('TICKET-062', 'Closed', 'Add Keycloak support for passwordless login', 'Feature', '2024-12-28', 'High', 'Passwordless login implemented successfully'),
('TICKET-063', 'Resolved', 'Fix Keycloak email verification delays', 'Bug', '2024-12-27', 'Medium', 'Verification emails now sent immediately'),
('TICKET-064', 'Open', 'Implement Keycloak custom OTP generator', 'Feature', '2025-01-09', 'High', 'Custom OTP generator requested for compliance'),
('TICKET-065', 'In Progress', 'Fix Keycloak login retries issue', 'Bug', '2025-01-08', 'High', 'Multiple retries allowed without lockout'),
('TICKET-066', 'Closed', 'Add Keycloak support for dynamic client registration', 'Feature', '2024-12-30', 'Low', 'Dynamic client registration working as expected'),
('TICKET-067', 'Resolved', 'Fix Keycloak token introspection errors', 'Bug', '2024-12-29', 'Medium', 'Token introspection API functioning properly'),
('TICKET-068', 'Open', 'Enhance Keycloak admin dashboard analytics', 'Enhancement', '2025-01-09', 'Medium', 'New widgets needed for user insights'),
('TICKET-069', 'In Progress', 'Fix Keycloak slow query execution', 'Bug', '2025-01-09', 'Critical', 'Specific queries causing delays in production'),
('TICKET-070', 'Closed', 'Implement Keycloak custom logout endpoint', 'Feature', '2024-12-28', 'High', 'Logout endpoint deployed successfully'),
('TICKET-071', 'Resolved', 'Fix Keycloak client credential grant errors', 'Bug', '2024-12-31', 'Medium', 'Client credentials working for all environments'),
('TICKET-072', 'Open', 'Add Keycloak support for password complexity rules', 'Feature', '2025-01-09', 'High', 'Complexity rules to match compliance'),
('TICKET-073', 'In Progress', 'Fix Keycloak account locking issue', 'Bug', '2025-01-08', 'High', 'Accounts not locking after max login failures'),
('TICKET-074', 'Closed', 'Optimize Keycloak federation query performance', 'Enhancement', '2024-12-27', 'Low', 'Query performance improved by 40%'),
('TICKET-075', 'Resolved', 'Fix Keycloak realm import/export issue', 'Bug', '2024-12-29', 'High', 'Realm data imports and exports working correctly'),
('TICKET-076', 'Open', 'Implement Keycloak theme for mobile responsiveness', 'Enhancement', '2025-01-09', 'Medium', 'Theme adjustments required for better mobile UI'),
('TICKET-077', 'In Progress', 'Fix Keycloak admin user deletion issue', 'Bug', '2025-01-08', 'High', 'Admin users cannot be deleted from specific realms'),
('TICKET-078', 'Closed', 'Add Keycloak custom error messages support', 'Feature', '2024-12-31', 'Low', 'Custom error messages now configurable'),
('TICKET-079', 'Resolved', 'Fix Keycloak login flow inconsistencies', 'Bug', '2024-12-30', 'Medium', 'All login flows behaving consistently'),
('TICKET-080', 'Open', 'Enhance Keycloak token exchange functionality', 'Feature', '2025-01-09', 'High', 'Token exchange for external IDPs required'),
('TICKET-081', 'In Progress', 'Fix Keycloak forgotten password flow issue', 'Bug', '2025-01-08', 'Critical', 'Forgotten password emails not being sent'),
('TICKET-082', 'Closed', 'Optimize Keycloak token cache performance', 'Enhancement', '2024-12-28', 'Low', 'Token cache hit rate improved by 50%'),
('TICKET-083', 'Resolved', 'Fix Keycloak API permission assignments', 'Bug', '2024-12-30', 'High', 'Permissions correctly assigned to API roles'),
('TICKET-084', 'Open', 'Add Keycloak admin console usage metrics', 'Feature', '2025-01-09', 'Medium', 'Metrics requested for admin activity tracking'),
('TICKET-085', 'In Progress', 'Fix Keycloak group role inheritance issue', 'Bug', '2025-01-08', 'High', 'Group roles not inherited as expected'),
('TICKET-086', 'Closed', 'Implement Keycloak customizable session timeouts', 'Feature', '2024-12-29', 'Low', 'Session timeouts now configurable per realm'),
('TICKET-087', 'Resolved', 'Fix Keycloak integration with third-party IDPs', 'Bug', '2024-12-31', 'High', 'All IDP integrations tested successfully'),
('TICKET-088', 'Open', 'Enhance Keycloak audit log export functionality', 'Enhancement', '2025-01-09', 'Medium', 'Support requested for CSV and JSON export formats'),
('TICKET-089', 'In Progress', 'Fix Keycloak user consent revocation issue', 'Bug', '2025-01-08', 'High', 'Consent revocation not removing access'),
('TICKET-090', 'Closed', 'Optimize Keycloak backup and restore process', 'Task', '2024-12-28', 'Low', 'Backup and restore times reduced significantly'),
('TICKET-091', 'Resolved', 'Fix Keycloak default roles assignment issue', 'Bug', '2024-12-30', 'Medium', 'Default roles now assigned correctly on user creation'),
('TICKET-092', 'Open', 'Implement Keycloak real-time notifications for admins', 'Feature', '2025-01-09', 'High', 'Real-time alerts for critical events requested'),
('TICKET-093', 'In Progress', 'Fix Keycloak database connection pool issues', 'Bug', '2025-01-08', 'Critical', 'Connection pool exhausted during peak times'),
('TICKET-094', 'Closed', 'Add Keycloak custom attribute support for clients', 'Enhancement', '2024-12-27', 'Low', 'Custom attributes added for client configurations'),
('TICKET-095', 'Resolved', 'Fix Keycloak token refresh flow issue', 'Bug', '2024-12-31', 'High', 'Token refresh working across all scenarios'),
('TICKET-096', 'Open', 'Enhance Keycloak logging verbosity controls', 'Feature', '2025-01-09', 'Medium', 'Granular logging levels requested for troubleshooting'),
('TICKET-097', 'In Progress', 'Fix Keycloak multi-realm session conflicts', 'Bug', '2025-01-08', 'High', 'Sessions conflicting between realms under load'),
('TICKET-098', 'Closed', 'Implement Keycloak admin console branding support', 'Feature', '2024-12-28', 'Low', 'Branding customization deployed successfully'),
('TICKET-099', 'Resolved', 'Fix Keycloak user federation sync issues', 'Bug', '2024-12-30', 'Medium', 'Federation sync schedules corrected'),
('TICKET-100', 'Open', 'Add Keycloak support for advanced reporting', 'Feature', '2025-01-09', 'High', 'Reports needed for compliance audits'),
('TICKET-101', 'In Progress', 'Fix Keycloak OAuth2 token validation errors', 'Bug', '2025-01-08', 'Critical', 'Token validation fails for specific audiences'),
('TICKET-102', 'Closed', 'Optimize Keycloak API rate limiting logic', 'Enhancement', '2024-12-28', 'Low', 'Rate limiting logic improved for large-scale clients'),
('TICKET-103', 'Resolved', 'Fix Keycloak user session termination delays', 'Bug', '2024-12-30', 'Medium', 'Sessions now terminated instantly on logout'),
('TICKET-104', 'Open', 'Enhance Keycloak role-based access control (RBAC)', 'Feature', '2025-01-09', 'High', 'Granular RBAC required for new clients'),
('TICKET-105', 'In Progress', 'Fix Keycloak token hashing algorithm mismatch', 'Bug', '2025-01-08', 'High', 'Algorithm mismatch causing validation errors'),
('TICKET-106', 'Closed', 'Implement Keycloak audit trail archiving', 'Task', '2024-12-29', 'Low', 'Audit trails archived automatically'),
('TICKET-107', 'Resolved', 'Fix Keycloak external IDP token exchange issue', 'Bug', '2024-12-31', 'High', 'Token exchange tested for all IDPs'),
('TICKET-108', 'Open', 'Add Keycloak support for scheduled user deactivation', 'Feature', '2025-01-09', 'Medium', 'Deactivation schedules required for compliance'),
('TICKET-109', 'In Progress', 'Fix Keycloak user invitation flow bugs', 'Bug', '2025-01-08', 'High', 'Invitations not reaching intended users'),
('TICKET-110', 'Closed', 'Enhance Keycloak client session management', 'Enhancement', '2024-12-28', 'Low', 'Client sessions managed efficiently'),
('TICKET-111', 'Resolved', 'Fix Keycloak admin console slow loading', 'Bug', '2024-12-30', 'Medium', 'Admin console loading times improved significantly'),
('TICKET-112', 'Open', 'Implement Keycloak email notification templates', 'Feature', '2025-01-09', 'High', 'Custom templates needed for email notifications'),
('TICKET-113', 'In Progress', 'Fix Keycloak realm duplication on export', 'Bug', '2025-01-08', 'Critical', 'Duplicate realms created during exports'),
('TICKET-114', 'Closed', 'Add Keycloak auto-scaling support', 'Task', '2024-12-27', 'Low', 'Auto-scaling enabled for high availability'),
('TICKET-115', 'Resolved', 'Fix Keycloak password policy migration issues', 'Bug', '2024-12-31', 'High', 'Password policies successfully migrated'),
('TICKET-116', 'Open', 'Enhance Keycloak API usage metrics', 'Feature', '2025-01-09', 'Medium', 'Metrics needed for API traffic analysis'),
('TICKET-117', 'In Progress', 'Fix Keycloak token encryption errors', 'Bug', '2025-01-08', 'High', 'Encryption errors impacting security audits'),
('TICKET-118', 'Closed', 'Implement Keycloak tenant isolation improvements', 'Feature', '2024-12-28', 'Low', 'Tenant isolation strengthened for multi-tenant environments'),
('TICKET-119', 'Resolved', 'Fix Keycloak consent approval issues', 'Bug', '2024-12-30', 'Medium', 'Consent approval flows validated successfully'),
('TICKET-120', 'Open', 'Add Keycloak support for biometric login', 'Feature', '2025-01-09', 'High', 'Biometric authentication requested for mobile clients');


```

