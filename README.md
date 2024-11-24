# User Management

This program provides a **User Management System** that supports standard HTTP methods – **GET**, **POST**, **PUT**, and
**DELETE** – for managing user data in a **PostgreSQL** database. It also includes features for sorting and searching by
terms.

### Purpose

The program is designed for **demonstrative purposes** and can be used as:

- A **REST API**, to integrate with external applications.
- A **Web API**, for web-based interfaces.

### Example Use Cases

- Managing user accounts for a web application.
- Demonstrating **CRUD operations** (Create, Read, Update, Delete) with a relational database.

### Technology Stack

- **Programming Language**: Java
- **Database**: PostgreSQL

## Table of contents

- [Requirements](#Requirements)
- [Installation](#Installation)
- [Configuration](#Configuration)
- [Usage](#Usage)
- [Issues](#Issues)
- [Maintainers](#Maintainers)

## Requirements

### Required

1. **Java**: Installed Java 17 or higher. [Download Java](https://www.oracle.com/java/technologies/downloads/#java21)
2. **IDE**: IntelliJ IDEA Community Edition (or any IDE). [Download IntelliJ](https://www.jetbrains.com/idea/download)
3. **Database**: PostgreSQL 16. [Download PostgreSQL](https://www.postgresql.org/download/)

### Optional

1. **API Testing Tool**: Postman. Useful for testing endpoints. [Download Postman](https://www.postman.com/downloads/)
2. **Database GUI Tool**: PGAdmin4. Recommended for managing your database
   easily. [Download PGAdmin4](https://www.pgadmin.org/download/)

## Installation

1. Make sure all **Requirements** are installed.
2. Clone the repository:
   ```bash
   git clone <https://github.com/hristodimitrovyordanov/usermanagement.git>
   cd <usermanagement>
   ```
3. Open the project in IntelliJ IDEA (or your preferred IDE).
4. Continue to the **Configuration** section to set up the database.

## Configuration

1. **Set Up the Database**:
    - Open **PGAdmin4** or any other database management tool.
    - Create a new database:
        - **Name**: `data_user`
        - **User**: `postgres`
        - **Password**: `<your-password>`
    - Ensure PostgreSQL is running on port `5432` (default port).

2. **Execute SQL Script(Optional)**:
    - If you'd like to initialize the database schema, execute the creation_database_data_user.sql file:
   ```bash
   psql -U postgres -d data_user -f src/main/resources/db/scripts/creation_database_data_user.sql
   ```
3. **Update Configuration Files**:
   Open `application.properties` and configure the database connection:
   ```properties
   spring.datasource.url=jdbc:postgresql://localhost:5432/data_user
   spring.datasource.username=postgres
   spring.datasource.password=<your-password>

4. **Open your IDE (e.g., IntelliJ IDEA), navigate to the main class located
   at:`src/main/java/com/hristo/usermanagement/UserManagementApplication.java`, and run it.**

5. **Alternative (Run from Command Line):** If you prefer to run the application from the command line, use the
   following command:
    ```bash
    mvn spring-boot:run
    ```

## Usage

### Overview

After running the application, it will automatically create:

1. 100 users in a table called users.
    - These users will have randomly generated:
        - First names
        - Last names
        - Dates of birth
    - Additionally, each user will have unique phone numbers and emails.
    - This pre-populated data allows for easier demonstration of standard HTTP methods (`GET`, `POST`, `PUT`, `DELETE`)
      and additional functionalities like sorting and searching.
2. Two additional tables:
    - `members`: Contains user credentials (usernames, encrypted passwords) for three roles.
    - `roles`: Defines the roles and associated permissions.

### Roles and Permissions

The program supports **three roles** with different levels of access to functionalities:

| Role     | Username | Password     | Access Rights                                    |
|----------|----------|--------------|--------------------------------------------------|
| EMPLOYEE | employee | employee1234 | Can only execute GET requests.                   |
| MANAGER  | manager  | manager1234  | Can execute GET, POST, and PUT requests.         |
| ADMIN    | admin    | admin1234    | Can execute GET, POST, PUT, and DELETE requests. |

- All passwords are encrypted using **bcrypt** and stored securely in the `members` table.

### How to Use

The program supports both **Web API** and **REST API**.

1. #### WEB API
    - **Step 1**: Start the application.
    - **Step 2**: Open your browser and navigate to:
       ```bash
       http://localhost:8080/login
       ```
    - **Step 3**: Log in using one of the roles listed above.
    - **Step 4**: Once logged in, you will be redirected to a page with buttons for different actions.
        - **Available Buttons**:
            - **Nine GET request buttons**: Perform various queries like searching or sorting.
            - **Create User (POST)**: Add a new user.
            - **Update User (PUT)**: Update user details.
            - **Delete User (DELETE)**: Remove a user.
            - **Logout**: Log out from the system.
    - **Step 5**: Click the buttons to execute the respective actions. Each button is labeled with the action it
      performs.
      #### Button Descriptions

        - **Authorization Prompt**:  
          When you click on any button, you will be asked to authorize your role again.

        - **Get All Users**:

            - Displays a paginated table of 10 users showing all their data.
            - Below the table are page links to navigate through additional users.
            - A **"User Management"** button allows you to return to the homepage.

        - **Get User by ID**:

            - Opens a page with an input field for entering the user ID.
            - **Validation**: The field must not be empty, and only numbers are allowed.
            - **Result**:
                - If the ID exists, the user’s details are displayed.
                - If the ID doesn’t exist, you are redirected to an error page.
            - The error page includes a **"Go back to the homepage"** button.

        - **Get User by Email**:

            - Opens a page with an input field for entering the user’s email.
            - **Validation**: The field must not be empty, and only valid email addresses are allowed.
            - **Result**:
                - If the email exists, the user’s details are displayed.
                - If the email doesn’t exist, you are redirected to an error page.
            - The error page includes a **"Go back to the homepage"** button.

        - **Get User by Phone Number**:

            - Opens a page with an input field for entering the user’s phone number.
            - **Validation**: The field must not be empty.
            - **Result**:
                - If the phone number exists, the user’s details are displayed.
                - If the phone number doesn’t exist, you are redirected to an error page.
            - The error page includes a **"Go back to the homepage"** button.

        - **Get Users by Specific First Name**:

            - Opens a page with an input field for entering the first name.
            - **Validation**: The field must not be empty.
            - **Result**:
                - If the first name exists, a paginated list of matching users is displayed.
                - If no users exist, a page with a **"User Management"** button will appear, allowing you to return to
                  the homepage.

        - **Get Users by Specific Last Name**:

            - Opens a page with an input field for entering the last name.
            - **Validation**: The field must not be empty.
            - **Result**:
                - If the last name exists, a paginated list of matching users is displayed.
                - If no users exist, a page with a **"User Management"** button will appear, allowing you to return to
                  the homepage.

        - **Get Users by Date of Birth**:

            - Opens a page with an input field for entering the date of birth.
            - **Validation**: The field must not be empty and must follow the required format.
            - **Result**:
                - If the date of birth exists, a paginated list of matching users is displayed.
                - If no users exist, a page with a **"User Management"** button will appear, allowing you to return to
                  the homepage.

        - **Get Users Sorted by Last Name**:

            - Displays a paginated table of 10 users sorted by last name in ascending order.
            - Below the table are page links to navigate through additional users.
            - A **"User Management"** button allows you to return to the homepage.

        - **Get Users Sorted by Date of Birth**:

            - Displays a paginated table of 10 users sorted by date of birth in ascending order.
            - Below the table are page links to navigate through additional users.
            - A **"User Management"** button allows you to return to the homepage.

        - **Create user**:

            - Clicking the Create User button opens a page with input fields to add a new user.
            - **Fields**:
                - **First Name**: Text field (required).
                - **Last Name**: Text field (required).
                - **Date of Birth**: Enter a date in a specific format (required).
                - **Phone Number**: Text field (required).
                - **Email**: Text field (required).
            - After filling in all fields correctly, click **Create User** to submit.
            - A success message with the user’s details will be displayed.
            - Use the **User Management** button to return to the homepage.

        - **Update User**:
            - Clicking the **Update User** button opens a page with an input field for the User ID to be updated.
                - **Process**:
                    - Enter an existing User ID.
                    - A page with the user’s current details will appear.
                    - Update the fields (first name, last name, date of birth, phone number, and email).
                    - Click Edit User to save changes.
                    - A success page will display the updated user details.
                - **Error Handling**:
                    - If a non-existent ID is entered, you will be redirected to an error page with a Go back to
                      homepage button.
                - Use the **User Management** button to return to the homepage.

        - **Delete User**
            - Clicking the Delete User button opens a page with an input field for the User ID to be deleted.
                - **Process**:
                    - Enter an existing User ID.
                    - Click **Delete User** to remove the user from the database.
                    - A success page will display a confirmation message.
                - **Error Handling**:
                    - If a non-existent ID is entered, you will be redirected to an error page with a Go back to
                      homepage button.
                - Use the **User Management** button to return to the homepage.

        - **Logout**
            - Clicking the Logout button will log you out of the system.
            - You can then switch to another account with a different access level.
                - **Example**
                    - If you are logged in as EMPLOYEE and need to create a new user (an action restricted to MANAGER or
                      ADMIN), log out and log in with the appropriate account.
            - After logging out, you can re-enter credentials to access the system with the desired role.

2. #### REST API
    - You can interact with the program via **Postman** or similar tools to send HTTP requests to the API endpoints:
        1. **Start the Application**:
          - Launch the **User Management** application and wait for it to load.
        2. **Authorize in Postman**
            - Go to the **Authorization** tab in Postman.
            - Select **Basic Auth** from the dropdown menu.
            - Enter the **Username** and **Password** for one of the roles:
                - Example: 
                  - **Username**: `employee`
                  - **Password**: `employee1234`
        3. **Choose HTTP Method**:
            - Select the request type (e.g., **GET**, **POST**) from the dropdown menu next to the address bar.
        4. **Set the Endpoint**:
            - Enter the appropriate URL
                - Example: To retrieve all users, use:
                    ``` bash
                    http://localhost:8080/user-management/users
                    ```
        5. **Send the Request**:
            - Click **Send** to execute the request.
            - Postman will display the response, such as a JSON list of users for a GET request.
        6. **API Documentation**:
           - For details on all available requests and their endpoints, visit the Swagger documentation:
                ```bash
                http://localhost:8080/swagger-ui/index.html
                ```
             - Note: Swagger also requires authorization with one of the roles.
                 

## Issues

If you encounter any problems, please report them
via [GitHub Issues](https://github.com/hristodimitrovyordanov/usermanagement/issues) or contact the maintainers.

## Maintainers

- [Hristo Dimitrov Yordanov](https://github.com/hristodimitrovyordanov)
- Email: [mrhristo29@gmail.com](mailto:mrhristo29@gmail.com)

