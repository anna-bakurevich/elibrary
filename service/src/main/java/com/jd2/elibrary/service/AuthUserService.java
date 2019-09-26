package com.jd2.elibrary.service;

import com.jd2.elibrary.model.User;

public interface AuthUserService {
    boolean userIsExist(String login, String password);

    User getUserByLogin(String login);

    void addUser(String login, String password);
}
