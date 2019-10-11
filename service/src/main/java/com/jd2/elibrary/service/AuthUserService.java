package com.jd2.elibrary.service;

import com.jd2.elibrary.model.Book;
import com.jd2.elibrary.model.User;

import java.util.List;

public interface AuthUserService {
    List<User> getUsers();

    List<Book> getBooks();

    User login(String login, String password);

    User id(int id);

    boolean loginIsExist(String login);

    int getIdByLogin(String login);

    void saveUser(User user);

    void removeUser(int id);

    void updateUser(User user);

    boolean idIsExist(int id);
}
