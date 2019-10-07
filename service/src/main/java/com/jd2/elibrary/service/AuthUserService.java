package com.jd2.elibrary.service;

import com.jd2.elibrary.model.User;

import java.sql.SQLException;

public interface AuthUserService {
    boolean userIsExist(String login, String password) throws SQLException,
            IllegalAccessException, InstantiationException, ClassNotFoundException;

    int getIdByLoginAndPassword(String login, String password) throws SQLException,
            IllegalAccessException, ClassNotFoundException, InstantiationException;

    void saveUser(User user) throws SQLException, IllegalAccessException, ClassNotFoundException, InstantiationException;
}
