package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {


    public UserDaoHibernateImpl() {

    }


    @Override
    public void createUsersTable() {
        Transaction transaction = null;
        try (Session session = Util.getSession().openSession()){
             transaction = session.beginTransaction();
            session.createNativeQuery("CREATE TABLE IF NOT EXISTS users" +
                    "(id SERIAL primary key, name varchar(20), lastName varchar(20), age INT)").executeUpdate();
            transaction.commit();
            System.out.println("___________________________________________________________________Была создана таблица" +
                    "___________________________________________________________________");


        } catch (HibernateException e) {
            e.printStackTrace();
            if (transaction != null) {
                transaction.rollback();
            }
        }

    }

    @Override
    public void dropUsersTable() {
        Transaction transaction = null;
        try(Session session = Util.getSession().openSession()){
            transaction = session.beginTransaction();
            session.createNativeQuery("DROP TABLE IF EXISTS users").executeUpdate();
            transaction.commit();
            System.out.println("___________________________________________________________________Таблица удалена" +
                    "___________________________________________________________________");

        } catch (HibernateException e) {
            e.printStackTrace();
            if (transaction != null) {
                transaction.rollback();
            }
        }

    }

    @Override
    public void saveUser(String name, String lastName, byte age) {

        Transaction transaction = null;
        try(Session session = Util.getSession().openSession()) {
            transaction = session.beginTransaction();
            session.save(new User(name, lastName, age));
            transaction.commit();
            System.out.println("___________________________________________________________________User создан" +
                    "___________________________________________________________________");
        } catch (HibernateException e) {
            e.printStackTrace();
            if (transaction != null) {
                transaction.rollback();
            }
        }

    }

    @Override
    public void removeUserById(long id) {

        Transaction transaction = null;
        try(Session session =Util.getSession().openSession();) {
            transaction = session.beginTransaction();
            session.delete(session.get(User.class, id));
            transaction.commit();
            System.out.println("___________________________________________________________User удален " +
                    "___________________________________________________________________");

        } catch (HibernateException e) {
            e.printStackTrace();
            if (transaction != null) {
                transaction.rollback();
            }
        }

    }

    @Override
    public List<User> getAllUsers() {
        List<User> list = new ArrayList<>();

        Transaction transaction = null;
        try(Session session = Util.getSession().openSession()) {
            transaction = session.beginTransaction();
            list = session.createCriteria(User.class).list();
            transaction.commit();
            System.out.println(list);
        } catch (HibernateException e) {
            e.printStackTrace();
            if (transaction != null) {
                transaction.rollback();
            }
        }
        return list;
    }



    @Override
    public void cleanUsersTable() {

        Transaction transaction = null;
        try (Session session = Util.getSession().openSession();){
            transaction = session.beginTransaction();
            session.createNativeQuery("DELETE FROM users").executeUpdate();
            transaction.commit();
            System.out.println("___________________________________________________________________Таблица пуста" +
                    "___________________________________________________________________");
        } catch (HibernateException e) {
            e.printStackTrace();
            if (transaction != null) {
                transaction.rollback();
            }
        }

    }

    }

