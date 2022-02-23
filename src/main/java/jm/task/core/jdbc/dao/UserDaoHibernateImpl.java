package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;

import jm.task.core.jdbc.util.Util;
import org.hibernate.*;
import org.hibernate.engine.transaction.internal.TransactionImpl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class UserDaoHibernateImpl implements UserDao {


    private SessionFactory sessionFactory;

    public UserDaoHibernateImpl() {
        sessionFactory = Util.getSessionFactory();
    }


    @Override
    public void createUsersTable() {
        String sql = "CREATE TABLE IF NOT EXISTS users " +
                "(id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY, " +
                "name VARCHAR(50) NOT NULL, lastName VARCHAR(50) NOT NULL, " +
                "age TINYINT NOT NULL)";

        Transaction transaction = null;
        try (Session session = sessionFactory.getCurrentSession()) {
            transaction = session.beginTransaction();
            session.createSQLQuery(sql).executeUpdate();
            transaction.commit();
            System.out.println("Table is created or exists");
        } catch (HibernateException e) {
            System.out.println("Во время создания таблицы возникла ошибка");
            e.printStackTrace();

        }

    }

    @Override
    public void dropUsersTable() {
        String sql = "drop TABLE IF EXISTS users";

        Transaction transaction = null;
        try (Session session = sessionFactory.getCurrentSession()) {
            transaction = session.beginTransaction();
            session.createSQLQuery(sql).executeUpdate();
            transaction.commit();
            System.out.println("Table is dropped or doesn't exist");
        } catch (HibernateException e) {
            System.out.println("Во время удаления таблицы возникла ошибка");

        }

    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        User user = new User(name, lastName, age);

        Transaction transaction = null;
        try (Session session = sessionFactory.getCurrentSession()) {
            transaction = session.beginTransaction();
            session.save(user);
            transaction.commit();
            System.out.println("Пользователь с именем " + user.getName() + " сохранён.");
        } catch (HibernateException e) {
            System.out.println("Во время добавления пользователя возникла ошибка");

        }

    }

    @Override
    public void removeUserById(long id) {

        Transaction transaction = null;
        try (Session session = sessionFactory.getCurrentSession()) {
            transaction = session.beginTransaction();
            User user = session.get(User.class, id);
            session.remove(user);
            transaction.commit();
            System.out.println("Пользователь с именем " + user.getName() + " удалён.");
        } catch (HibernateException e) {
            System.out.println("Во время удаления пользователя возникла ошибка");

        }
    }

    @Override
    public List<User> getAllUsers() {
        List<User> usersList = new ArrayList<>();

        Transaction transaction = null;
        try (Session session = sessionFactory.getCurrentSession()) {
            transaction = session.beginTransaction();
            usersList = session.createQuery("from User").getResultList();
            transaction.commit();
        } catch (HibernateException e) {
            System.out.println("Во время получения списка пользователей возникла ошибка");

        }
        return usersList;
    }

    @Override
    public void cleanUsersTable() {
        String sql = "truncate TABLE users";

        Transaction transaction = null;
        try (Session session = sessionFactory.getCurrentSession()) {
            transaction = session.beginTransaction();
            session.createSQLQuery(sql).executeUpdate();
            transaction.commit();
            System.out.println("Table is truncated or doesn't exist");
        } catch (HibernateException e) {
            System.out.println("Во время получения списка пользователей возникла ошибка");

        }

    }
}
