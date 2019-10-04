package com.jd2.elibrary.service.impl;

import com.jd2.elibrary.dao.impl.DefaultUserDao;
import com.jd2.elibrary.dao.UserDao;
import com.jd2.elibrary.model.User;
import com.jd2.elibrary.service.AuthUserService;

import java.sql.SQLException;

public class DefaultAuthUserService implements AuthUserService {
    private UserDao userDao = DefaultUserDao.getInstance();

    private static AuthUserService instance;

    public static synchronized AuthUserService getInstance() {
        if (instance == null) {
            instance = new DefaultAuthUserService();
        }
        return instance;
    }


    @Override
    public boolean userIsExist(String login, String password) {
        boolean result = false;

        for (User user : userDao.getUsers()) {
            if (user.getLogin().equals(login) && user.getPassword().equals(password)) {
                result = true;
                break;
            }
        }
        return result;
    }

    @Override
    public User getUserByLogin(String login) throws SQLException {
        return userDao.getUserByLogin(login);
    }

    @Override
    public void addUser(User user) throws SQLException {
        userDao.addUser(user);
    }
}
