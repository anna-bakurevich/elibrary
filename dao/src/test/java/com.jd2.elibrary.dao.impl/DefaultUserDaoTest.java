package com.jd2.elibrary.dao.impl;

import com.jd2.elibrary.model.entity.User;
import com.jd2.elibrary.model.util.EMUtil;
import org.junit.jupiter.api.Test;

import javax.persistence.EntityManager;

import static org.junit.jupiter.api.Assertions.*;


public class DefaultUserDaoTest {
    DefaultUserDao dao = DefaultUserDao.getInstance();

    @Test
    void getUsersTest() {
        assertNotNull(dao.getUsers());
    }

    @Test
    void idIsExistTest() {
        assertTrue(dao.idIsExist(1));
    }

    @Test
    void getIdByLoginTest() {
        assertEquals(1, dao.getIdByLogin("Anna"));
    }

    @Test
    void saveUser() {
        User user = new User();
        EntityManager em = EMUtil.getEntityManager();
        em.getTransaction().begin();
        em.persist(user);
        em.getTransaction().commit();
    }
}
