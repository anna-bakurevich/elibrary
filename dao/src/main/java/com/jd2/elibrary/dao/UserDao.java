package com.jd2.elibrary.dao;

import com.jd2.elibrary.model.User;

import java.sql.SQLException;
import java.util.List;

public interface UserDao {
    //create
    void saveUser(User user) throws SQLException, IllegalAccessException, InstantiationException, ClassNotFoundException;

    //read
    List<User> getUsers() throws SQLException, IllegalAccessException, InstantiationException, ClassNotFoundException;

    int getIdByLoginAndPassword(String login, String password) throws SQLException, IllegalAccessException,
            InstantiationException, ClassNotFoundException;

    //update
    void updateUser(User user);

    //delete
    void removeUser(User user) throws SQLException, IllegalAccessException, InstantiationException, ClassNotFoundException;
}
