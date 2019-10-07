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
    public boolean userIsExist(String login, String password) throws SQLException, IllegalAccessException,
            InstantiationException, ClassNotFoundException {

        boolean result = false;
        //если вернется id!=0, то пользователь с таким логином и паролем уже существует в базе
        if (getIdByLoginAndPassword(login, password) != 0) {
            result = true;
        }
        return result;
    }

    @Override
    public int getIdByLoginAndPassword(String login, String password) throws SQLException, IllegalAccessException,
            ClassNotFoundException, InstantiationException {
        return userDao.getIdByLoginAndPassword(login, password);
    }

    @Override
    public void saveUser(User user) throws SQLException, IllegalAccessException, ClassNotFoundException, InstantiationException {
        userDao.saveUser(user);
    }
}
