package com.jd2.elibrary.dao;

import com.jd2.elibrary.model.User;

import java.util.ArrayList;
import java.util.List;

public class DefaultUserDao implements UserDao{

    private static DefaultUserDao instance;

    public static synchronized DefaultUserDao getInstance(){
        if(instance==null){
            instance = new DefaultUserDao();
        }
        return instance;
    }

    private List<User> users = new ArrayList<>();

    @Override
    public List<User> getUsers() {
        return users;
    }

    @Override
    public void addUser(String login, String password) {
     users.add(new User(login, password));
    }

    @Override
    public User getUserByLogin(String login) {
       User user = null;
        for (User u:users) {
            if (u.getLogin().equals(login)) {
                user = u;
                break;
            }
        }
        return user;
    }

    @Override
    public void updateUser(User user) {

    }

    @Override
    public void removeUser(User user) {

    }
}
