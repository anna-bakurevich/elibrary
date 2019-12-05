package com.jd2.elibrary.dao;

import com.jd2.elibrary.model.User;

import java.util.List;

public interface UserDao {
    //create
    int save(User user);

    //read
    List<User> findAll();

    User findByLogin(String login);

    User findById(int id);

    int getIdByLogin(String login);

    boolean existsById(int id);

    //update
    void update(User user, String firstName, String lastName, String phone);

    //delete
    void deleteById(int id);
}
