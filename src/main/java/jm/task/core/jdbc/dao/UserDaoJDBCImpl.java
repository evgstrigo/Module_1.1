package jm.task.core.jdbc.dao;

import com.mysql.cj.MysqlConnection;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.MySQLConnUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class UserDaoJDBCImpl implements UserDao {
    static Connection connection;

//    static {
//        try {
//            connection = MySQLConnUtils.getMySQLConnection();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        }
//    }

    public UserDaoJDBCImpl() throws SQLException, ClassNotFoundException {
        connection = MySQLConnUtils.getMySQLConnection();
    }

    public void createUsersTable() throws SQLException {
        String sql = "create table kata_db.Users (\n" +
                "ID integer PRIMARY KEY AUTO_INCREMENT,\n" +
                "NAME varchar(30),\n" +
                "LASTNAME varchar(30),\n" +
                "AGE integer\n" +
                ")";
        Statement statement = connection.createStatement();
        try {
            statement.executeUpdate(sql);
            System.out.println("Table USERS is CREATED");
        } catch (SQLSyntaxErrorException e) {
            System.out.println("Table with the same name \"USERS\" exists");
        }
    }

    public void dropUsersTable() throws SQLException {
        String sql = "drop table users";
        Statement statement = connection.createStatement();
        try {
            statement.executeUpdate(sql);
            System.out.println("Table USERS is DROPPED");
        } catch (SQLSyntaxErrorException e) {
            System.out.println("Table USERS doesn't exists");
        }
    }

    public void saveUser(String name, String lastName, byte age) throws SQLException {
        try {
            String sql = "insert into USERS (NAME, LASTNAME, AGE) values(  " +
                    "'" + name + "' ," +
                    " '" + lastName + "', " +
                    "" + age + ")";
            Statement statement = connection.createStatement();
            statement.executeUpdate(sql);
            System.out.println("Пользователь " + name + " " + lastName + " добавлен");
        } catch (SQLSyntaxErrorException e) {
            System.out.println("Ошибка добавления пользователя");
        }

    }

    public void removeUserById(long id) {

    }

    public List<User> getAllUsers() throws SQLException {
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

        } catch (SQLSyntaxErrorException e) {
            System.out.println("Ошибка выполнения операции");
        }

        return allUsersList;
    }

    public void cleanUsersTable() throws SQLException {
        String sql = "truncate table users";
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate(sql);
            System.out.println("Таблица очищена");
        } catch (SQLSyntaxErrorException e) {
            System.out.println("Не удалось очистить таблицу. Вероятно, её не существует");
        }

    }
}
