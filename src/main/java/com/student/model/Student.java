package com.student.model;

public class Student {
    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private int age;
    private String course;

    public String getMobile_no() {
        return mobile_no;
    }

    public void setMobile_no(String mobile_no) {
        this.mobile_no = mobile_no;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Student(String mobile_no, String address, String course, int age, String email, String firstName, String lastName, int id, String gender) {
        this.mobile_no = mobile_no;
        this.address = address;
        this.course = course;
        this.age = age;
        this.email = email;
        this.lastName = lastName;
        this.firstName = firstName;
        this.id = id;
        this.gender = gender;
    }

    private String address;
    private String gender;
    private String mobile_no;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Student() {
    }



    public Student(String mobile_no, String address, String course, String email, String firstName, String lastName, int age, String gender) {
        this.mobile_no = mobile_no;
        this.address = address;
        this.course = course;
        this.age = age;
        this.email = email;
        this.lastName = lastName;
        this.firstName = firstName;
        this.gender = gender;
    }
    @Override
    public String toString() {
        return "\n----Student Details:----" +
                "\n------------------------" +
                "\nID       : " + id +
                "\nFirstName: " + firstName +
                "\nLastName : " + lastName +
                "\nEmail    : " + email +
                "\nAge      : " + age +
                "\ncourse   : " + course +
                "\ngender   : " + gender +
                "\naddress  : "  +address+
                "\nmobile_no: " + mobile_no +
                "\n";
    }
}
