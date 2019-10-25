package com.jd2.elibrary.dao.impl;

import com.jd2.elibrary.dao.DataSource;
import com.jd2.elibrary.dao.UserDao;
import com.jd2.elibrary.dao.entity.UserEntity;
import com.jd2.elibrary.dao.util.EMUtil;
import com.jd2.elibrary.model.Role;
import com.jd2.elibrary.model.User;

import javax.persistence.EntityManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DefaultUserDao implements UserDao {
    private static DefaultUserDao instance;

    public static synchronized DefaultUserDao getInstance() {
        if (instance == null) {
            instance = new DefaultUserDao();
        }
        return instance;
    }

    private Connection connect() {
        return DataSource.getInstance().getConnection();
    }

    @Override
    public int saveUser(User user) {

        UserEntity userEntity = new UserEntity();
        userEntity.setId(user.getId());
        userEntity.setFirstName(user.getFirstName());
        userEntity.setLastName(user.getLastName());
        userEntity.setPhone(user.getPhone());
        userEntity.setLogin(user.getLogin());
        userEntity.setPassword(user.getPassword());
        userEntity.setRole(user.getRole());

        EntityManager em = EMUtil.getEntityManager();
        em.getTransaction().begin();
        em.persist(userEntity);
        em.getTransaction().commit();
        return userEntity.getId();

//        try (Connection connection = connect();
//             PreparedStatement preparedStatement = connection.prepareStatement(
//                     "INSERT INTO user(first_name, last_name, phone, login, password, role) VALUES (?,?,?,?,?,?)",
//                     Statement.RETURN_GENERATED_KEYS)) {
//            preparedStatement.setString(1, user.getFirstName());
//            preparedStatement.setString(2, user.getLastName());
//            preparedStatement.setString(3, user.getPhone());
//            preparedStatement.setString(4, user.getLogin());
//            preparedStatement.setString(5, user.getPassword());
//            preparedStatement.setString(6, Role.CUSTOMER.name());
//            preparedStatement.executeUpdate();
//            try (ResultSet keys = preparedStatement.getGeneratedKeys()) {
//                keys.next();
//                user.setId(keys.getInt(1));
//                return user.getId();
//            }
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
    }

    @Override
    public void updateUser(User user) {

        EntityManager em = EMUtil.getEntityManager();

        em.getTransaction().begin();
        UserEntity userEntity = em.find(UserEntity.class, user.getId());
        userEntity.setFirstName(user.getFirstName());
        userEntity.setLastName(user.getLastName());
        userEntity.setPhone(user.getPhone());
        em.getTransaction().commit();


//        try (Connection connection = connect();
//             PreparedStatement preparedStatement = connection.prepareStatement("UPDATE user SET first_name=?, " +
//                     "last_name=?, phone=? WHERE id=?")) {
//            preparedStatement.setString(1, user.getFirstName());
//            preparedStatement.setString(2, user.getLastName());
//            preparedStatement.setString(3, user.getPhone());
//            preparedStatement.setInt(4, user.getId());
//            preparedStatement.executeUpdate();
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
    }

    @Override
    public void removeUser(int id) {

        EntityManager em = EMUtil.getEntityManager();

        em.getTransaction().begin();
        UserEntity userEntity = em.find(UserEntity.class, id);
        em.remove(userEntity);
        em.getTransaction().commit();
//
//        try (Connection connection = connect();
//             PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM user WHERE id=?")) {
//            preparedStatement.setInt(1, id);
//            preparedStatement.executeUpdate();
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
    }

    @Override
    public List<User> getUsers() {
        try (Connection connection = connect();
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM user");
             ResultSet resultSet = preparedStatement.executeQuery()) {
            final ArrayList<User> users = new ArrayList<>();
            while (resultSet.next()) {
                final User user = new User(
                        resultSet.getInt("id"),
                        resultSet.getString("first_name"),
                        resultSet.getString("last_name"),
                        resultSet.getString("phone"),
                        resultSet.getString("login"),
                        resultSet.getString("password"));
                users.add(user);
            }
            return users;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public User getByLogin(String login) {
        try (Connection connection = connect();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "SELECT * FROM user WHERE login = ?")) {
            preparedStatement.setString(1, login);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return new User(
                            resultSet.getInt("id"),
                            resultSet.getString("first_name"),
                            resultSet.getString("last_name"),
                            resultSet.getString("phone"),
                            resultSet.getString("login"),
                            resultSet.getString("password"),
                            Role.valueOf(resultSet.getString("role")
                            ));
                } else {
                    return null;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public User getById(int id) {
        try (Connection connection = connect();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "SELECT * FROM user WHERE id = ?")) {
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return new User(
                            resultSet.getInt("id"),
                            resultSet.getString("first_name"),
                            resultSet.getString("last_name"),
                            resultSet.getString("phone"),
                            resultSet.getString("login"),
                            resultSet.getString("password"),
                            Role.valueOf(resultSet.getString("role")
                            ));
                } else {
                    return null;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean idIsExist(int id) {
        EntityManager em = EMUtil.getEntityManager();
        if (em.find(UserEntity.class, id) != null) {
           return true;
        }
        return false;
//        try (Connection connection = connect();
//             PreparedStatement preparedStatement = connection.prepareStatement(
//                     "SELECT * FROM user WHERE id = ?")) {
//            preparedStatement.setInt(1, id);
//            try (ResultSet resultSet = preparedStatement.executeQuery()) {
//                if (resultSet.next()) {
//                    return true;
//                }
//            }
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//        return false;
    }

    @Override
    public int getIdByLogin(String login) {
        try (Connection connection = connect();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "SELECT * FROM user WHERE login = ?")) {
            preparedStatement.setString(1, login);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getInt("id");
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return 0;
    }
}
