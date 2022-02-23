package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
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
    private final static String HIBERNATE_DIALECT = "org.hibernate.dialect.MySQLDialect";

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

     //Connect by HIBERNATE
    public static SessionFactory getSessionFactory() {

        try {
            Properties prop = new Properties();
            prop.setProperty("hibernate.connection.url", CONNECTION_URL);
            prop.setProperty("hibernate.connection.username", USER_NAME);
            prop.setProperty("hibernate.connection.password", PASSWORD);
            prop.setProperty("hibernate.dialect", HIBERNATE_DIALECT);
            prop.setProperty("hibernate.current_session_context_class", "thread");
            prop.setProperty("hibernate.hbm2ddl.auto", "update");
            prop.setProperty("show_sql", "true");


            Configuration configuration = new Configuration()
                    .addProperties(prop)
                    .addAnnotatedClass(User.class);

            ServiceRegistry sr = new StandardServiceRegistryBuilder()
                    .applySettings(configuration.getProperties())
                    .build();

            return configuration.buildSessionFactory(sr);
        } catch (Exception ex) {
            throw new ExceptionInInitializerError(ex);
        }
    }


}
