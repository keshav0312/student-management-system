package com.student.util;



import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;
import java.io.InputStream;


public class DBConnection {
    private static String url;
    private static String user;
    private static String pass;


    //load the properties configuration only at the time of class  loading dont need to load manually again and again
    static {
        try
        {
            //fetch the username , pass,url from application properties file
            InputStream in = DBConnection.class.getClassLoader().getResourceAsStream("application.properties");
            Properties p = new Properties();
            p.load(in);
            url = p.getProperty("db.url");
            user = p.getProperty("db.user");
            pass = p.getProperty("db.password");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // method to return  connection we can this where we need connection object
    public static Connection get() throws Exception {
        return DriverManager.getConnection(url, user, pass);
    }
}
