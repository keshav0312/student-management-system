package com.student.service;

import com.student.dao.StudentDAO;
import com.student.dao.StudentDAOImpl;
import com.student.model.Student;

import java.sql.SQLException;
import java.util.List;
import java.util.regex.Pattern;

public class StudentService {

    private final StudentDAO dao = new StudentDAOImpl();
    private final Pattern emailPattern = Pattern.compile("^[A-Za-z0-9+_.-]+@(.+)$");

    public Student addStudent(Student s) throws Exception {
        validate(s, false);
        return dao.add(s);
    }

    public Student getById(int id) throws Exception {
        return dao.findById(id);
    }

    public List<Student> getAll() throws Exception {
        return dao.findAll();
    }

    public boolean updateStudent(Student s) throws Exception {
        validate(s, true);
        return dao.update(s);
    }

    public boolean deleteStudent(int id) throws Exception {
        return dao.deleteById(id);
    }

    private void validate(Student s, boolean updateOrAdd) throws Exception {
        if (dao.checkEmailExits(s.getEmail()))
            throw new SQLException("Email already exists...");
        if (!s.getMobile_no().matches("^[0-9]{10}$"))
            throw new SQLException("Mobile number is not valid...");
        if (updateOrAdd && s.getId() <= 0)
            throw new RuntimeException("Invalid id for update please enter correct id");
        if (s.getFirstName() == null || s.getFirstName().trim().isEmpty())
            throw new RuntimeException("first name is required");
        if (s.getEmail() == null || !emailPattern.matcher(s.getEmail()).matches())
            throw new RuntimeException("enter valid email");
        if (!s.getGender().contains("male") || s.getGender().contains("female"))
            throw new RuntimeException("enter valid gender");
        if (s.getAge() < 0 || s.getAge() > 150)
            throw new RuntimeException("Enter valid age..");
    }

    public Student searchByName(String name) throws SQLException {
        if (name == null || name.trim().isEmpty())
            throw new RuntimeException("name is required...");
        return dao.searchByName(name);
    }
}
