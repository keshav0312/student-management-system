package com.student.dao;

import com.student.model.Student;
import com.student.util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentDAOImpl implements StudentDAO {

    @Override
    public Student add(Student s) throws Exception {
        String sql = "INSERT INTO students (first_name, last_name, email, age, gender, course,address,mobile_no) VALUES (?, ?, ?, ?,?,?,?,?)";

        Connection con = null;
        PreparedStatement ps = null;

        try {
            con = DBConnection.get();   // get connection object
            ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            ps.setString(1, s.getFirstName());
            ps.setString(2, s.getLastName());
            ps.setString(3, s.getEmail());
            ps.setInt(4, s.getAge());
            ps.setString(5, s.getGender());
            ps.setString(6, s.getCourse());
            ps.setString(7, s.getAddress());
            ps.setString(8, s.getMobile_no());

            ps.executeUpdate();   // insert done

            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                s.setId(rs.getInt(1));  // set generated id
            }
            return s;
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        } finally {
            if (ps != null) {
                ps.close();
            }
            if (con != null) {
                con.close();
            }
        }
    }

    //Method is used to find students by there id
    @Override
    public Student findById(int id) throws Exception {
        String sql = "SELECT * FROM students WHERE id = ?";

        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            con = DBConnection.get(); // get connection
            ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if (rs.next()) {
                return mapRow(rs);  // map result to object
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw e; // rethrow exception
        } finally {
            if (ps != null) {
                ps.close();
            }
            if (con != null) {
                con.close();
            }
            if (rs != null) {
                rs.close();
            }
        }

        return null; // no student found
    }

    // find all students
    @Override
    public List<Student> findAll() throws Exception {

        String sql = "SELECT id, first_name, last_name, email, age,gender,course,address,mobile_no FROM students";
        List<Student> list = new ArrayList<>();
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            con = DBConnection.get(); //get Connection
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                list.add(mapRow(rs)); //  Convert each row to Student object
            }

        } catch (Exception e) {
            e.printStackTrace();
            throw e; // Optional: let caller handle exception

        } finally {
            if (ps != null) {
                ps.close();
            }
            if (con != null) {
                con.close();
            }
            if (rs != null) {
                rs.close();
            }
        }

        return list;
    }

    //update student on the basif of there id
    @Override
    public boolean update(Student s) throws Exception {

        String sql = "UPDATE students SET first_name = ?, last_name = ?, email = ?, age = ? ,gender=?,course=?,address=?,mobile_no=? WHERE id = ?";

        Connection con = null;
        PreparedStatement ps = null;

        try {
            con = DBConnection.get();   // Get db connection
            ps = con.prepareStatement(sql);

            ps.setString(1, s.getFirstName());
            ps.setString(2, s.getLastName());
            ps.setString(3, s.getEmail());
            ps.setInt(4, s.getAge());
            ps.setString(5, s.getGender());
            ps.setString(6, s.getCourse());
            ps.setString(7, s.getAddress());
            ps.setString(8, s.getMobile_no());
            ps.setInt(9, s.getId());

            int rows = ps.executeUpdate();
            return rows > 0; //

        } catch (Exception e) {
            e.printStackTrace();
            throw e; //

        } finally {

            //  Close PreparedStatement
            try {
                if (ps != null) ps.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }

            //  Close Connection
            try {
                if (con != null) con.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }


    //delete students by there id
    @Override
    public boolean deleteById(int id) throws Exception {

        String sql = "DELETE FROM students WHERE id = ?";
        Connection con = null;
        PreparedStatement ps = null;

        try {

            con = DBConnection.get();   //  get connection
            ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            int rows = ps.executeUpdate();

            return rows > 0; // returns true if delete success

        } catch (Exception e) {
            e.printStackTrace();
            throw e; // pass exception

        } finally {

            //  close PreparedStatement
            try {
                if (ps != null) ps.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }

            //  close Connection
            try {
                if (con != null) con.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    //search student by name
    @Override
    public Student searchByName(String name) throws SQLException {
        PreparedStatement preparedStatement = null;
        Connection connection = null;
        try {
            connection = DBConnection.get();
            preparedStatement = connection.prepareStatement("SELECT * FROM students WHERE first_name LIKE ?");
            preparedStatement.setString(1, "%" + name + "%");
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                return mapRow(rs);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
        return null;
    }

    @Override
    public boolean checkEmailExits(String email) throws Exception {
        PreparedStatement preparedStatement = null;
        Connection connection = null;
        try {

            String sql = "select * from students where email = ?";
            connection = DBConnection.get();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, email);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                if (rs.getString("email").equals(email)) {
                    return true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
        finally {
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
        return false;
    }


    private Student mapRow(ResultSet rs) throws SQLException {
        Student s = new Student();
        s.setId(rs.getInt("id"));
        s.setFirstName(rs.getString("first_name"));
        s.setLastName(rs.getString("last_name"));
        s.setEmail(rs.getString("email"));
        s.setAge(rs.getInt("age"));
        s.setGender(rs.getString("gender"));
        s.setCourse(rs.getString("course"));
        s.setAddress(rs.getString("address"));
        s.setMobile_no(rs.getString("mobile_no"));
        return s;
    }
}
