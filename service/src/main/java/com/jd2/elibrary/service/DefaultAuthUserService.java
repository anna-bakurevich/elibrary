package com.jd2.elibrary.service;

import com.jd2.elibrary.dao.DefaultUserDao;
import com.jd2.elibrary.dao.UserDao;
import com.jd2.elibrary.model.User;

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
    public User getUserByLogin(String login) {
        return userDao.getUserByLogin(login);
    }

    @Override
    public void addUser(String login, String password) {
        userDao.addUser(login, password);
    }
}
