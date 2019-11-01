package com.jd2.elibrary.dao.impl;

import com.jd2.elibrary.dao.DataSource;
import com.jd2.elibrary.dao.UserDao;
import com.jd2.elibrary.dao.entity.UserEntity;
import com.jd2.elibrary.dao.util.EMUtil;
import com.jd2.elibrary.dao.util.EntityUtil;
import com.jd2.elibrary.model.Role;
import com.jd2.elibrary.model.User;
import org.hibernate.Session;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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

        UserEntity userEntity = EntityUtil.convertToUserEntity(user);

        EntityManager em = EMUtil.getEntityManager();
        em.getTransaction().begin();
        em.persist(userEntity);
        em.getTransaction().commit();
        return userEntity.getId();
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
        em.close();
    }

    @Override
    public void removeUser(int id) {

        EntityManager em = EMUtil.getEntityManager();

        em.getTransaction().begin();
        UserEntity userEntity = em.find(UserEntity.class, id);
        em.remove(userEntity);
        em.getTransaction().commit();
        em.close();
    }

    @Override
    public boolean findById(int id) {
        EntityManager em = EMUtil.getEntityManager();
        if (em.find(UserEntity.class, id) != null) {
            return true;
        }
        return false;
    }

    @Override
    public List<User> getUsers() {
        Session session = EMUtil.getSession();
        Query query = session.createQuery("from UserEntity" );
                return query.getResultList();
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
