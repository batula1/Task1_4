package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;


public class Util {




    private Util() {

    }


    private static Properties getProperties() {
        Properties properties = new Properties();
        try (InputStream in = Util.class.getClassLoader().getResourceAsStream("application.properties")) {
            properties.load(in);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return properties;
    }

    public static Connection getConnection() {
        Properties prop = getProperties();
         Connection connect = null;
        try  {connect = DriverManager.getConnection(
                prop.getProperty("db.url"),
                prop.getProperty("db.username"),
                prop.getProperty("db.password"));
            if(connect != null){
                System.out.println("connection");
            }else {
                System.out.println("connection failed ");
            }

        } catch (SQLException exp){
            System.err.format("SQL State: %s\n%s", exp.getSQLState(), exp.getMessage());
        } catch (Exception exp){
            exp.printStackTrace();
        }
        return connect;

    }
    public static SessionFactory getSession() {
        SessionFactory sessionFactory = null;
        try {
            Configuration configuration = new Configuration()
                    .setProperty("hibernate.connection.driver_class","org.postgresql.Driver")
                    .setProperty("hibernate.connection.url","jdbc:postgresql://localhost:5432/postgres")
                    .setProperty("hibernate.connection.username", "postgres")
                    .setProperty("hibernate.connection.password", "589948")
                    .setProperty("hibernate.dialect", "org.hibernate.dialect.PostgreSQL95Dialect")
                    .addAnnotatedClass(User.class);
             sessionFactory = configuration.buildSessionFactory();

        } catch (HibernateException e) {
            throw new RuntimeException(e);
        }catch (Throwable ex) {
            throw new ExceptionInInitializerError(ex);
        }

          return sessionFactory ;

    }


}



