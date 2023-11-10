package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {


        public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
            try(Statement statement = Util.getConnection().createStatement()){
                statement.executeUpdate("CREATE TABLE IF NOT EXISTS users " +
                        "(id SERIAL primary key, name varchar(20), lastName varchar(20), age INT)");
            } catch (SQLException exp) {
                exp.printStackTrace();
            }
    }

    public void dropUsersTable() {
            try(Statement statement = Util.getConnection().createStatement()){
                statement.executeUpdate("DROP TABLE IF EXISTS users");

            } catch (SQLException exp) {
                exp.printStackTrace();
            }

    }

    public void saveUser(String name, String lastName, byte age) {
            try (PreparedStatement addUserTab = Util.getConnection().prepareStatement(
                    "INSERT INTO users (name, lastName, age) VALUES (?, ?, ?)")){
                addUserTab.setString(1, name);
                addUserTab.setString(2, lastName);
                addUserTab.setByte(3, age);
                addUserTab.executeUpdate();

            } catch (SQLException exp) {
                exp.printStackTrace();
            }
    }


    public void removeUserById(long id) {
            try (PreparedStatement delUserTab = Util.getConnection().prepareStatement("DELETE FROM users WHERE id = ?")){
                delUserTab.setLong(1,id);
                delUserTab.executeUpdate();

            } catch (SQLException exp) {
                exp.printStackTrace();
            }
    }




    public List<User> getAllUsers() {
            List<User> list = new ArrayList<>();
            try (ResultSet resSet =  Util.getConnection().prepareStatement(
                    "SELECT * FROM users").executeQuery()
            ){
                while (resSet.next()){
                    User user = new User(
                            resSet.getString(2),
                            resSet.getString(3),
                            resSet.getByte(4));
                    user.setId(resSet.getLong("id"));
                    list.add(user);
                    System.out.println(user);

                }


            } catch (SQLException exp) {
                exp.printStackTrace();
            }
        return list;
    }

    public void cleanUsersTable() {
        try (Statement statement = Util.getConnection().createStatement()) {
            statement.executeUpdate("TRUNCATE TABLE users");
        } catch (SQLException exp) {
            exp.printStackTrace();
        }

    }
}
