# TDD - Student Management System

## 1. Project Structure (Folders)
model → Stores Student class  
dao → Contains database code  
service → Contains logic  
ui → Contains Main (menu)  
util → Contains DBConnection file

## 2. Classes and Their Work 

### Class: Student (model)
- Variables: id, name, email,age, course, address, gender, mobile_no
- Used to store student information.

### Class: StudentDAO (dao - Interface)
- Contains method names (add, getAll, update, delete, checkEmailExits, findById, checkEmailExits, findById).

### Class: StudentDAOImpl (dao - Implementation)
- Contains actual JDBC code to connect and run SQL queries.

### Class: StudentService (service)
- Calls DAO methods.
- Acts like a middle layer between UI and Database it provide main logic how the db and interact each other..

### Class: DBConnection (util)
- Gives database connection using DriverManager.

### Class: Main (ui)
- Shows menu to user.
- Takes input from user and calls particular method.
- Calls StudentService methods.

## 3. Database Table Used
Table Name: students

Columns:
- id (int, auto increment)
- firstname (varchar)
- lastname (varchar)
- email (varchar unique)
- age (int)
- gender
- mobile_no
- address
- course