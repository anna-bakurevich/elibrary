package com.jd2.elibrary.service.impl.impl;


import com.jd2.elibrary.dao.UserDao;
import com.jd2.elibrary.model.User;
import com.jd2.elibrary.service.impl.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class DefaultUserService implements UserService {

    private final UserDao defaultUserDao;

    public DefaultUserService(UserDao defaultUserDao) {
        this.defaultUserDao = defaultUserDao;
    }

    @Override
    @Transactional
    public int save(User user) {
        return defaultUserDao.save(user);
    }

    @Override
    @Transactional
    public List<User> findAll() {
        return defaultUserDao.findAll();
    }

    @Override
    @Transactional
    public void deleteById(int id) {
        defaultUserDao.deleteById(id);
    }

    @Override
    @Transactional
    public void update(User user, String firstName, String lastName, String phone) {
        defaultUserDao.update(user, firstName, lastName, phone);
    }

    @Override
    @Transactional
    public boolean existsById(int id) {
        return defaultUserDao.existsById(id);
    }

    @Override
    @Transactional
    public User login(String login, String password) {
        User user = defaultUserDao.findByLogin(login);
        if (user == null) {
            return null;
        }
        if (user.getPassword().equals(password)) {
            return user;
        }
        return null;
    }

    @Override
    @Transactional
    public User findById(int id) {
        return defaultUserDao.findById(id);
    }

    @Override
    @Transactional
    public boolean loginIsExist(String login) {
        //если вернется id!=0, то пользователь с таким логином уже есть в базе
        if (getIdByLogin(login) != 0) {
            return true;
        }
        return false;
    }

    @Override
    @Transactional
    public int getIdByLogin(String login) {
        return defaultUserDao.getIdByLogin(login);
    }

}
