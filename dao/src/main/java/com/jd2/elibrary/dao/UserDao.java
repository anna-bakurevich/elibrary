package com.jd2.elibrary.dao;

import com.jd2.elibrary.model.User;

import java.util.List;

public interface UserDao {
    //create
    void addUser(String login, String password);

    //read
    List<User> getUsers();

    User getUserByLogin(String login);

    //update
    void updateUser(User user);

    //delete
    void removeUser(User user);
}
