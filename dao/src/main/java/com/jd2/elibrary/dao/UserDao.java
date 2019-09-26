package com.jd2.elibrary.dao;

import com.jd2.elibrary.model.User;

import java.util.List;

public interface UserDao {
    List<User> getUsers();
    void addUser(String login, String password);
    User getUserByLogin (String login);
}
