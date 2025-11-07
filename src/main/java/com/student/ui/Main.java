package com.student.ui;

import com.student.model.Student;
import com.student.service.StudentService;

import java.util.List;
import java.util.Scanner;

public class Main {
    private static final Scanner sc = new Scanner(System.in);
    private static final StudentService service = new StudentService();

    public static void main(String[] args) {

        System.out.println("=== > Student Management System  <===");

        boolean check = true;
        //show menu bar while user not enter 0 or exist
        while (check) {
            StudentMenu();
            String input = sc.nextLine().trim();
            switch (input) {
                case "1":
                    add();
                    break;
                case "2":
                    viewById();
                    break;
                case "3":
                    update();
                    break;
                case "4":
                    delete();
                    break;
                case "5":
                    listAll();
                    break;
                case "6":
                    searchByName();
                    break;
                case "0":
                    check = false;
                    break;
                default:
                    System.out.println("Invalid option Try again and choose correct option....");
            }
        }
        sc.close(); //close scanner class object
    }


    //reusable Menu
    private static void StudentMenu() {
        System.out.println("\n1. Add Student");
        System.out.println("2. View Student by ID");
        System.out.println("3. Update Student");
        System.out.println("4. Delete Student");
        System.out.println("5. List All Students");
        System.out.println("6. Search By Name");
        System.out.println("0. Exit");
        System.out.print("Choose option: ");
    }


    //add student
    private static void add() {
        try {
            System.out.print("Enter your first name: ");
            String fName = sc.nextLine().trim();
            System.out.print("Enter your Last name: ");
            String lName = sc.nextLine().trim();
            System.out.print("Enter your Email: ");
            String email = sc.nextLine().trim();
            System.out.print("Enter your Age: ");
            int age = Integer.parseInt(sc.nextLine().trim());
            System.out.print("Enter your course: ");
            String course = sc.nextLine().trim();
            System.out.print("Enter your gender: ");
            String gender = sc.nextLine().trim();
            System.out.print("Enter your mobile number: ");
            String mobile_no = sc.nextLine().trim();
            System.out.print("Enter your address: ");
            String address = sc.nextLine().trim();
            Student s = new Student(mobile_no,address,course,email,fName,lName,age,gender);
            Student saved = service.addStudent(s);
            System.out.println("Student Added->" + saved);
        } catch (Exception e) {
            System.err.println("Error while adding student....: " + e.getMessage());
        }
    }

    //search student by name
    private static void searchByName() {
        try {
            System.out.print("Enter name you want to search..");
            String name = sc.nextLine().trim();
            Student student = service.searchByName(name);
            if (student != null) {
                System.out.println("Student Found->" + student);
            } else {
                System.out.println("Student Not Found");
            }

        } catch (Exception e) {
            System.err.println("Error....: " + e.getMessage());
        }
    }

    //view student
    private static void viewById() {
        try {
            System.out.print("Enter id: ");
            int id = Integer.parseInt(sc.nextLine().trim());
            Student s = service.getById(id);
            if (s == null) throw new RuntimeException("Student not found enter correct id..");
            else System.out.println(s);
        } catch (Exception e) {
            System.err.println("Error : " + e.getMessage());
        }
    }

    //update student
    private static void update() {
        try {
            System.out.print("Enter id which you want to update: ");
            int id = Integer.parseInt(sc.nextLine().trim());
            Student existing = service.getById(id);
            if (existing == null) {
                throw new RuntimeException("Student not found enter correct id..");
            }
            System.out.print("First name (" + existing.getFirstName() + "): ");
            String fn = sc.nextLine().trim();
            if (fn.isEmpty()) fn = existing.getFirstName();
            System.out.print("Last name (" + existing.getLastName() + "): ");
            String ln = sc.nextLine().trim();
            if (ln.isEmpty()) ln = existing.getLastName();
            System.out.print("Email (" + existing.getEmail() + "): ");
            String email = sc.nextLine().trim();
            if (email.isEmpty()) email = existing.getEmail();
            System.out.print("Age (" + existing.getAge() + "): ");
            String ageStr = sc.nextLine().trim();
            int age = ageStr.isEmpty() ? existing.getAge() : Integer.parseInt(ageStr);
            System.out.print("Gender (" + existing.getGender() + "): ");
            String gender = sc.nextLine().trim();
            if (gender.isEmpty()) gender = existing.getGender();
            System.out.print("course (" + existing.getCourse() + "): " );
            String course = sc.nextLine().trim();
            if (course.isEmpty()) course = existing.getCourse();
            System.out.print("address (" + existing.getAddress() + "): " );
            String address = sc.nextLine().trim();
            if (address.isEmpty()) address = existing.getAddress();
            System.out.print("mobile_no(" + existing.getMobile_no() + "): ");
            String mobile_no = sc.nextLine().trim();
            if (mobile_no.isEmpty()) mobile_no = existing.getMobile_no();
            Student updated = new Student(mobile_no,address,course,age,email,fn,ln,id,gender);
            boolean done = service.updateStudent(updated);
            System.out.println(done ? "Updated student successfully." : "student Update failed.");
        } catch (Exception e) {
            System.err.println("Error while updating student: " + e.getMessage());
        }
    }

    //delete student
    private static void delete() {
        try {
            System.out.print("Enter id to delete: ");
            int id = Integer.parseInt(sc.nextLine().trim());
            boolean deleted = service.deleteStudent(id);
            System.out.println(deleted ? " Student Deleted successfully..." : "student delete failed or id not found.");
        } catch (Exception e) {
            System.err.println("Error deleting student: " + e.getMessage());
        }
    }

    private static void listAll() {
        try {
            List<Student> list = service.getAll();
            if (list.isEmpty()) {
               throw new Exception("Student list is empty");
            }
            System.out.println("------Students list----");
            list.forEach((student) -> System.out.println(student));
        } catch (Exception e) {
            System.err.println("Error listing students please try again: " + e.getMessage());
        }
    }
}
