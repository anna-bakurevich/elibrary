package com.jd2.elibrary.dao.impl;

import com.jd2.elibrary.dao.UserDao;
import com.jd2.elibrary.dao.converter.UserConverter;
import com.jd2.elibrary.dao.entity.UserEntity;
import com.jd2.elibrary.dao.util.EMUtil;
import com.jd2.elibrary.model.User;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;
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

    @Override
    public int saveUser(User user) {

        UserEntity userEntity = UserConverter.convertToUserEntity(user);

        final EntityManager em = EMUtil.getEntityManager();
        em.getTransaction().begin();
        em.persist(userEntity);
        em.getTransaction().commit();
        return userEntity.getId();
    }

    @Override
    public void updateUser(User user) {

        final EntityManager em = EMUtil.getEntityManager();

        em.getTransaction().begin();
        UserEntity userEntity = em.find(UserEntity.class, user.getId());
        userEntity.setFirstName(user.getFirstName());
        userEntity.setLastName(user.getLastName());
        userEntity.setPhone(user.getPhone());
        em.getTransaction().commit();
    }

    @Override
    public void removeUser(int id) {

        final EntityManager em = EMUtil.getEntityManager();

        em.getTransaction().begin();
        UserEntity userEntity = em.find(UserEntity.class, id);
        em.remove(userEntity);
        em.getTransaction().commit();
    }

    @Override
    public boolean findById(int id) {
        final EntityManager em = EMUtil.getEntityManager();
        if (em.find(UserEntity.class, id) != null) {
            return true;
        }
        return false;
    }

    @Override
    public List<User> getUsers() {
        final Session session = EMUtil.getSession();
        Query query = session.createQuery("from UserEntity");
        return query.getResultList();
    }

    @Override
    public User getByLogin(String login) {
        UserEntity userEntity;
        try {
            final Session session = EMUtil.getSession();
            Query query = session.createQuery("from UserEntity ue where ue.login = :login");
            userEntity = (UserEntity) query.setParameter("login", login)
                    .getSingleResult();

        } catch (NoResultException e) {
            log.info("user not found by login{}", login);
            userEntity = null;
        }
        return UserConverter.convertToUser(userEntity);
    }

    @Override
    public int getIdByLogin(String login) {
        UserEntity userEntity;
        int id;
        try {
            final Session session = EMUtil.getSession();
            Query query = session.createQuery("from UserEntity ue where ue.login = :login");
            userEntity = (UserEntity) query.setParameter("login", login)
                    .getSingleResult();
            id = userEntity.getId();
        } catch (NoResultException e) {
            log.info("id not found by login{}", login);
            id = 0;
        }
        return id;
    }


    @Override
    public User getById(int id) {
        final Session session = EMUtil.getSession();
        session.getTransaction().begin();
        UserEntity userEntity = session.find(UserEntity.class, id);
        session.getTransaction().commit();
        return UserConverter.convertToUser(userEntity);
    }

}
