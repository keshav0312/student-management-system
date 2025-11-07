# Student Management System (Console Application)

This is a simple Java-based console project that stores and manages student details.

## Features
- Add Student
- View All Students
- Search Student By Id
- Update Student
- search By Name
- Delete Student

## How to Run the Project
-Open Main class inside your project and click on run button..

#### Step 1: Create Database in MySQL
CREATE DATABASE student_db;

USE student_db;

**CREATE TABLE students (
id INT AUTO_INCREMENT PRIMARY KEY,
first_name VARCHAR(50) NOT NULL,
last_name VARCHAR(50),
email VARCHAR(150),
age INT,
gender VARCHAR(7),
address VARCHAR(100),
course VARCHAR(40),
mobile_no VARCHAR(12)
);**


#### Step 2: Set Database Username and Password
Go to application properties file under resources folder and set:
- username
- password

#### Step 3: Run the Program
- The program will now store data in MySQL.

## Learning Outcome
- Java Basics
- Object-Oriented Programming Concepts
- CRUD Operations
- JDBC Connection with MySQL
