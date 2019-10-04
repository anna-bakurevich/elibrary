package com.jd2.elibrary.dao;

import com.jd2.elibrary.model.User;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface UserDao {
    //create
    void addUser(User user) throws SQLException;

    //read
    List<User> getUsers() throws SQLException;

    User getUserByLogin(String login) throws SQLException;

    //update
    void updateUser(User user);

    //delete
    void removeUser(User user) throws SQLException;

    Connection connect() throws SQLException;
}
