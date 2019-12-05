package com.jd2.elibrary.service.impl;

import com.jd2.elibrary.model.User;

import java.util.List;

public interface UserService {
    int save(User user);

    List<User> findAll();

    void deleteById(int id);

    void update(User user, String firstName, String lastName, String phone);

    boolean existsById(int id);

    User login(String login, String password);

    User findById(int id);

    boolean loginIsExist(String login);

    int getIdByLogin(String login);





}
