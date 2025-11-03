package com.student.util;



import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;
import java.io.InputStream;

public class DBConnection {
    private static String url;
    private static String user;
    private static String pass;

    static {
        try (InputStream in = DBConnection.class.getClassLoader().getResourceAsStream("application.properties")) {
            Properties p = new Properties();
            p.load(in);
            url = p.getProperty("db.url");
            user = p.getProperty("db.user");
            pass = p.getProperty("db.password");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Connection get() throws Exception {
        return DriverManager.getConnection(url, user, pass);
    }
}
