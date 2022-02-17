package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;

import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.Transaction;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class UserDaoHibernateImpl implements UserDao {
    private static Session currentSession;

    static {
        try {
            currentSession = Util.getSession();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public UserDaoHibernateImpl() {

    }


    @Override
    public void createUsersTable() {
        String sql = "CREATE TABLE IF NOT EXISTS users " +
                "(id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY, " +
                "name VARCHAR(50) NOT NULL, lastName VARCHAR(50) NOT NULL, " +
                "age TINYINT NOT NULL)";
        currentSession.beginTransaction();
        currentSession.createSQLQuery(sql).executeUpdate();
        currentSession.getTransaction().commit();
        System.out.println("Table is created or exists");
    }

    @Override
    public void dropUsersTable() {
        String sql = "drop TABLE IF EXISTS users";
        currentSession.beginTransaction();
        currentSession.createSQLQuery(sql).executeUpdate();
        currentSession.getTransaction().commit();
        System.out.println("Table is dropped or doesn't exist");

    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        User user = new User(name, lastName, age);
        currentSession.beginTransaction();
        currentSession.save(user);
        currentSession.getTransaction().commit();
        System.out.println("Пользователь с именем " + user.getName() + " сохранён.");
    }

    @Override
    public void removeUserById(long id) {
        currentSession.beginTransaction();
        User user = currentSession.get(User.class, id);
        currentSession.remove(user);
        currentSession.getTransaction().commit();
        System.out.println("Пользователь с именем " + user.getName() + " удалён.");
    }

    @Override
    public List<User> getAllUsers() {
        List<User> usersList = new ArrayList<>();
        currentSession.beginTransaction();
        usersList = currentSession.createQuery("from User").getResultList();
        currentSession.getTransaction().commit();
        return usersList;
    }

    @Override
    public void cleanUsersTable() {
        String sql = "truncate TABLE users";
        Transaction t = currentSession.beginTransaction();
        currentSession.createSQLQuery(sql).executeUpdate();
        t.commit();
        System.out.println("Table is truncated or doesn't exist");
    }
}
