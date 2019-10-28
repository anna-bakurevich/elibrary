package com.jd2.elibrary.dao;

import com.jd2.elibrary.model.User;

import java.util.List;

public interface UserDao {
    //create
    int saveUser(User user);

    //read
    List<User> getUsers();


    //update
    void updateUser(User user);

    //delete
    void removeUser(int id);

    User getByLogin(String login);

    User getById(int id);

    int getIdByLogin(String login);

    boolean findById(int id);
}
