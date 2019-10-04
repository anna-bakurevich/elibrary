package com.jd2.elibrary.dao.impl;

import com.jd2.elibrary.dao.UserDao;
import com.jd2.elibrary.model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class DefaultUserDao implements UserDao {

    private static DefaultUserDao instance;

    public static synchronized DefaultUserDao getInstance(){
        if(instance==null){
            instance = new DefaultUserDao();
        }
        return instance;
    }

    @Override
    public Connection connect() throws SQLException {
        ResourceBundle resource = ResourceBundle.getBundle("db");
        String url = resource.getString("url");
        String user = resource.getString("user");
        String password = resource.getString("password");
        return DriverManager.getConnection(url, user, password);
    }

    @Override
    public void addUser(User user) throws SQLException {
        UserDao dataBase = DefaultUserDao.getInstance();
        try (Connection connection = dataBase.connect();
             PreparedStatement preparedStatement = connection.prepareStatement
                     ("insert into user(logine, password) values (?, ?)")) {
            preparedStatement.setString(1, user.getLogin());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.executeUpdate();
        }
    }

    @Override
    public List<User> getUsers() throws SQLException {
        List<User> users = new ArrayList<>();
        UserDao dataBase = DefaultUserDao.getInstance();

        try (Connection connection = dataBase.connect();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("select * from user")) {
            while (resultSet.next()) {
               User user = new User();
                user.setId(resultSet.getInt("id"));
                user.setFirst_name(resultSet.getString("first_name"));
                user.setLast_name(resultSet.getString("last_name"));
                user.setPhone(resultSet.getString("phone"));
                user.setLogin(resultSet.getString("login"));
                user.setPassword(resultSet.getString("password"));

                users.add(user);
            }
        }
        return users;
    }

    @Override
    public User getUserByLogin(String login) throws SQLException {
        UserDao dataBase = DefaultUserDao.getInstance();
        try(Connection connection = dataBase.connect();
        PreparedStatement preparedStatement = connection.prepareStatement("select * from user where login = ?")){
            preparedStatement.setString(1, login);
            ResultSet resultSet =  preparedStatement.executeQuery();
            if (resultSet.next()){
                return new User(login, resultSet.getString("password"));
            }
        }
        return null;
    }

    @Override
    public void updateUser(User user) {

    }

    @Override
    public void removeUser(User user) throws SQLException {
        UserDao dataBase = DefaultUserDao.getInstance();
        try (Connection connection = dataBase.connect();
             PreparedStatement preparedStatement = connection.prepareStatement("delete from book where id=?")) {

            preparedStatement.setInt(1, user.getId());
        }
    }


}
