package jm.task.core.jdbc.dao;


import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.MySQLConnUtils;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class UserDaoJDBCImpl implements UserDao {
    private static Connection connection;

    static {
        try {
            connection = MySQLConnUtils.getMySQLConnection();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public UserDaoJDBCImpl() {
    }


    public void createUsersTable() {
        try {
            String sql = "create table if not exists Users (\n" +
                    "ID integer PRIMARY KEY AUTO_INCREMENT,\n" +
                    "NAME varchar(30),\n" +
                    "LASTNAME varchar(30),\n" +
                    "AGE integer\n" +
                    ")";

            Statement statement = connection.createStatement();
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            System.out.println("Внезапно возникло исключение " + e);
        }
    }


    public void dropUsersTable() {
        try {
            String sql = "drop table if exists users";
            Statement statement = connection.createStatement();
            statement.executeUpdate(sql);
            System.out.println("Table USERS is DROPPED");
        } catch (SQLException e) {
            System.out.println("Внезапно возникло исключение " + e);
        }

    }

    public void saveUser(String name, String lastName, byte age) {
        try {
            String sql = "insert into USERS  (NAME, LASTNAME, AGE) values(  " +
                    "'" + name + "' ," +
                    " '" + lastName + "', " +
                    "" + age + ")";
            Statement statement = connection.createStatement();
            statement.executeUpdate(sql);
            System.out.println("User с именем – " + name + " добавлен в базу данных");
        } catch (SQLException e) {
            System.out.println("Ошибка добавления пользователя");
            System.out.println("Внезапно возникло исключение " + e);
        }

    }

    public void removeUserById(long id) {
        String sql = "delete from users where id = " + id;
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            System.out.println("Внезапно возникло исключение " + e);
        }
    }

    public List<User> getAllUsers() {
        List<User> allUsersList = new ArrayList<>();
        String sql = "select * from USERS";
        Long id;
        String name;
        String lastname;
        byte age;
        User tempUser;
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                id = resultSet.getLong("ID");
                name = resultSet.getString("NAME");
                lastname = resultSet.getString("LASTNAME");
                age = resultSet.getByte("AGE");
                tempUser = new User(name, lastname, age);
                tempUser.setId(id);
                allUsersList.add(tempUser);
            }
        } catch (SQLException e) {
            System.out.println("Внезапно возникло исключение " + e);
        }
        return allUsersList;
    }

    public void cleanUsersTable() {
        String sql = "truncate table users";
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate(sql);
            System.out.println("Таблица очищена");
        } catch (SQLException e) {
            System.out.println("Внезапно возникло исключение " + e);
        }

    }
}
