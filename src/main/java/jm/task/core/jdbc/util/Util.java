package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class Util {
    private static SessionFactory session;

    public Util() {
    }

    public static SessionFactory getSession() {
        try {
            session = new Configuration().addAnnotatedClass(User.class).buildSessionFactory();
        } catch (HibernateException e) {
            e.printStackTrace();
        }
        return session;

    }

    public static void closeConnect(){
        if(session != null){
            session.close();
        }
    }

}
