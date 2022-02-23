package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Util {
    private final static String CONNECTION_URL = "jdbc:mysql://localhost:3306/kata_db";
    private final static String USER_NAME = "root";
    private final static String PASSWORD = "123456";
    private final static String DRIVER_NAME = "com.mysql.cj.jdbc.Driver";

    private static Connection connection;

    public static Connection getConnection() {
        try {
            Class.forName(DRIVER_NAME);
            connection = DriverManager.getConnection(CONNECTION_URL, USER_NAME, PASSWORD);
            System.out.println("Connection opened");
        } catch (SQLException |
                ClassNotFoundException e) {
            System.out.println("Exception while opening connection");
        }
        return connection;
    }

    public static void closeConnection() {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                System.out.println("При закрытии Connection произошла ошибка");
            }
        }
    }






    // Connect to MySQL by HIBERNATE
    private static SessionFactory getSessionFactory() throws IOException {
        Properties properties = new Properties();
        properties.load(new FileReader(
                "C:\\Users\\sev\\IdeaProjects\\kata\\src\\main\\resources\\myHibernate.properties"));

        Configuration conf = new Configuration().setProperties(properties).addAnnotatedClass(User.class);
        ServiceRegistry sr = new StandardServiceRegistryBuilder().applySettings(conf.getProperties()).build();
        SessionFactory sf = conf.buildSessionFactory(sr);
        return sf;
    }


    public static Session getSession() throws IOException {
        return getSessionFactory().openSession();
    }


}
