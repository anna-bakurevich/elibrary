package com.jd2.elibrary.service.impl.impl;

import com.jd2.elibrary.dao.UserDao;
import com.jd2.elibrary.model.User;
import com.jd2.elibrary.service.impl.AuthUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DefaultAuthUserService implements AuthUserService {

    @Autowired
    private UserDao userDao;
            //= DefaultUserDao.getInstance();

//    private static AuthUserService instance;
//
//    public static synchronized AuthUserService getInstance() {
//        if (instance == null) {
//            instance = new DefaultAuthUserService();
//        }
//        return instance;
//    }

    @Override
    public List<User> getUsers() {
        return userDao.getUsers();
    }


    @Override
    public int saveUser(User user) {
       return userDao.saveUser(user);
    }


    @Override
    public void removeUser(int id) {
        userDao.removeUser(id);
    }

    @Override
    public void updateUser(User user) {
        userDao.updateUser(user);
    }

    @Override
    public boolean idIsExist(int id) {
        if (userDao.findById(id)){
            return true;
        }
        return false;
    }

    @Override
    public User login(String login, String password) {
        User user = userDao.getByLogin(login);
        if (user == null) {
            return null;
        }
        if (user.getPassword().equals(password)) {
            return user;
        }
        return null;
    }

    @Override
    public User id(int id) {
        return userDao.getById(id);
    }

    @Override
    public boolean loginIsExist(String login) {
        //если вернется id!=0, то пользователь с таким логином уже есть в базе
        if (getIdByLogin(login) != 0) {
            return true;
        }
        return false;
    }

    @Override
    public int getIdByLogin(String login) {
        return userDao.getIdByLogin(login);
    }

}
