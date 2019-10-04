package com.jd2.elibrary.service;

import com.jd2.elibrary.model.User;

import java.sql.SQLException;

public interface AuthUserService {
    boolean userIsExist(String login, String password);

    User getUserByLogin(String login) throws SQLException;

    void addUser(User user) throws SQLException;
}
