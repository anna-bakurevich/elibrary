package com.jd2.elibrary.dao.impl;

import com.jd2.elibrary.dao.DataSource;
import com.jd2.elibrary.dao.UserDao;
import com.jd2.elibrary.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DefaultUserDao implements UserDao {
    private static final Logger log = LoggerFactory.getLogger(DefaultUserDao.class);
    private static DefaultUserDao instance;

    public static synchronized DefaultUserDao getInstance() {
        if (instance == null) {
            instance = new DefaultUserDao();
        }
        return instance;
    }

    private Connection connect() throws IllegalAccessException, InstantiationException, ClassNotFoundException {
        return DataSource.getInstance().getConnection();
    }

    @Override
    public void saveUser(User user) throws IllegalAccessException, ClassNotFoundException, InstantiationException {
        try (Connection connection = connect();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "insert into user(login, password) values (?, ?)")) {
            preparedStatement.setString(1, user.getLogin());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<User> getUsers() throws IllegalAccessException, ClassNotFoundException, InstantiationException {
        List<User> users = new ArrayList<>();
        try (Connection connection = connect();
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
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    @Override
    public int getIdByLoginAndPassword(String login, String password) throws SQLException,
            IllegalAccessException, ClassNotFoundException, InstantiationException {
        try (Connection connection = connect();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "select * from user where login = ? and password = ?")) {
            preparedStatement.setString(1, login);
            preparedStatement.setString(2, password);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getInt("id");
                }
            }
        }
        return 0;
    }

    @Override
    public void updateUser(User user) {

    }

    @Override
    public void removeUser(User user) throws SQLException, IllegalAccessException, ClassNotFoundException,
            InstantiationException {
        try (Connection connection = connect();
             PreparedStatement preparedStatement = connection.prepareStatement("delete from user where id=?")) {

            preparedStatement.setInt(1, user.getId());
        }
    }


}
