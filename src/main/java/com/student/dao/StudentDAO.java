package com.student.dao;



import com.student.model.Student;
import java.sql.SQLException;
import java.util.List;


public interface StudentDAO {
    Student add(Student student) throws Exception;
    Student findById(int id) throws Exception;
    List<Student> findAll() throws Exception;
    boolean update(Student student) throws Exception;
    boolean deleteById(int id) throws Exception;
    Student searchByName(String name) throws SQLException;
    boolean checkEmailExits(String email) throws Exception;

}
