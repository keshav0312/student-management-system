import com.student.dao.StudentDAOImpl;
import com.student.model.Student;
import com.student.util.DBConnection;
import org.junit.jupiter.api.*;

import java.sql.Connection;
import java.sql.Statement;
import java.util.List;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class) // To run tests in order
public class StudentDAOImplTest {

    private static StudentDAOImpl dao;

    @BeforeAll
    public static void setupDatabase() throws Exception {
        dao = new StudentDAOImpl();

        Connection con = DBConnection.get();
        Statement st = con.createStatement();

        st.execute("CREATE TABLE students (" +
                "id INT AUTO_INCREMENT PRIMARY KEY, " +
                "first_name VARCHAR(50), " +
                "last_name VARCHAR(50), " +
                "email VARCHAR(50), " +
                "age INT, gender VARCHAR(10), " +
                "course VARCHAR(50), " +
                "address VARCHAR(100), " +
                "mobile_no VARCHAR(20))");

        con.close();
    }

    @Test
    @Order(1)
    public void testAddStudent() throws Exception {
        Student s = new Student();
        s.setFirstName("Keshav");
        s.setLastName("Choudhary");
        s.setEmail("test@mail.com");
        s.setAge(23);
        s.setGender("Male");
        s.setCourse("B.Tech");
        s.setAddress("Badgaon");
        s.setMobile_no("9023020223");

        Student saved = dao.add(s);
        Assertions.assertNotNull(saved.getId());
    }

    @Test
    @Order(2)
    public void testFindById() throws Exception {
        Student s = dao.findById(1);
        Assertions.assertNotNull(s);
        Assertions.assertEquals("Keshav", s.getFirstName());
    }

    @Test
    @Order(3)
    public void testFindAll() throws Exception {
        List<Student> list = dao.findAll();
        Assertions.assertTrue(list.size() > 0);
    }

    @Test
    @Order(4)
    public void testUpdateStudent() throws Exception {
        Student s = dao.findById(1);
        s.setFirstName("Keshav Updated");
        boolean result = dao.update(s);

        Assertions.assertTrue(result);

        Student updated = dao.findById(1);
        Assertions.assertEquals("Keshav Updated", updated.getFirstName());
    }

    @Test
    @Order(5)
    public void testCheckEmailExists() throws Exception {
        boolean exists = dao.checkEmailExits("test@mail.com");
        Assertions.assertTrue(exists);
    }

    @Test
    @Order(6)
    public void testDeleteStudent() throws Exception {
        boolean result = dao.deleteById(1);
        Assertions.assertTrue(result);

        Student s = dao.findById(1);
        Assertions.assertNull(s);
    }
}
