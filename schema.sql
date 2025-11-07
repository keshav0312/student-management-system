CREATE DATABASE  studentdb;
USE studentdb;

CREATE TABLE students
(
    id         INT AUTO_INCREMENT PRIMARY KEY,
    first_name VARCHAR(50) NOT NULL,
    last_name  VARCHAR(50),
    email      VARCHAR(150),
    age        INT,
    gender     VARCHAR(7),
    address    VARCHAR(100),
    course     VARCHAR(40),
    mobile_no  VARCHAR(12)
);
