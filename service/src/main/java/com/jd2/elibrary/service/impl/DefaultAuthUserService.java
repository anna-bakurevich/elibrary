package com.jd2.elibrary.service.impl;

import com.jd2.elibrary.dao.BookDao;
import com.jd2.elibrary.dao.UserDao;
import com.jd2.elibrary.dao.impl.DefaultBookDao;
import com.jd2.elibrary.dao.impl.DefaultUserDao;
import com.jd2.elibrary.model.Book;
import com.jd2.elibrary.model.User;
import com.jd2.elibrary.service.AuthUserService;

import java.util.List;

public class DefaultAuthUserService implements AuthUserService {
    private UserDao userDao = DefaultUserDao.getInstance();
    private BookDao bookDao = DefaultBookDao.getInstance();

    private static AuthUserService instance;

    public static synchronized AuthUserService getInstance() {
        if (instance == null) {
            instance = new DefaultAuthUserService();
        }
        return instance;
    }

    @Override
    public List<User> getUsers() {
        return userDao.getUsers();
    }

    @Override
    public List<Book> getBooks() {
        return bookDao.getBooks();
    }

    @Override
    public void saveUser(User user) {
        userDao.saveUser(user);
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
        return userDao.idIsExist(id);
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
