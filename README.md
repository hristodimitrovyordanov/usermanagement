# User Management

This program provides a **User Management System** that supports standard HTTP methods – **GET**, **POST**, **PUT**, and **DELETE** – for managing user data in a **PostgreSQL** database. It also includes features for sorting and searching by terms.

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
2. **Database GUI Tool**: PGAdmin4. Recommended for managing your database easily. [Download PGAdmin4](https://www.pgadmin.org/download/)


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

3. **Open your IDE (e.g., IntelliJ IDEA), navigate to the main class located at:`src/main/java/com/hristo/usermanagement/UserManagementApplication.java`, and run it.**

4. **Alternative (Run from Command Line):** If you prefer to run the application from the command line, use the following command:
    ```bash
    mvn spring-boot:run
    ```
   
## Usage

After the program is running, it will create 100 users in a table called 'users'. These 100 users will hava random 
first names, last names and dates of birth, but they also will have unique phone numbers and emails. The purpose of 
this is to fill the table with data, because it will be much easier to demonstrate the standard HTTP methods – 
GET, POST, PUT and DELETE. Besides that the program contains methods for sorting and searching. The program will create 
and other two tables - 'members' and 'roles'. They will contain a data for the three roles that can log in the program.
These three roles have a different usernames and password, also different access to the functionalities of the program.
The first role is 'EMPLOYEE' with username 'employee' and password 'employee1234', the second one is 'MANAGER' with 
username 'manager' and password 'manager1234' and the third one is 'ADMIN' with username 'admin' and 
password 'admin1234'. The passwords for all three are encrypted with 'bcrypt' and are located in table 'members'.



## Issues

If you encounter any problems, please report them via [GitHub Issues](https://github.com/hristodimitrovyordanov/usermanagement/issues) or contact the maintainers.


## Maintainers

- [Hristo Dimitrov Yordanov](https://github.com/hristodimitrovyordanov)
- Email: [mrhristo29@gmail.com](mailto:mrhristo29@gmail.com)