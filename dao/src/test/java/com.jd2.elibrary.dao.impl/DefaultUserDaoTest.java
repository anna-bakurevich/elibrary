package com.jd2.elibrary.dao.impl;

import com.jd2.elibrary.dao.entity.UserEntity;
import com.jd2.elibrary.dao.util.EMUtil;
import com.jd2.elibrary.model.Role;
import com.jd2.elibrary.model.User;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
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
    void saveUserTest() {
        User user = new User("Иван", "Иванов", "+375-29-334-52-76", "I", "1", Role.CUSTOMER);
        UserEntity userEntity = new UserEntity();
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

        em.clear();

        UserEntity userEntityFromDB = em.find(UserEntity.class, userEntity.getId());
        Assertions.assertEquals(userEntity.getLastName(), userEntityFromDB.getLastName());
    }

    @AfterAll
    public static void cleanUp() {
        EMUtil.closeEMFactory();
    }
}
