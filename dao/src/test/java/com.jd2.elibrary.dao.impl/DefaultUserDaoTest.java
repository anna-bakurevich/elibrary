package com.jd2.elibrary.dao.impl;

import com.jd2.elibrary.dao.entity.UserEntity;
import com.jd2.elibrary.dao.util.EMUtil;
import com.jd2.elibrary.dao.util.EntityUtil;
import com.jd2.elibrary.model.Role;
import com.jd2.elibrary.model.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import javax.persistence.EntityManager;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


public class DefaultUserDaoTest {
    DefaultUserDao dao = DefaultUserDao.getInstance();

    @Test
    void getUsersTest() {

        assertNotNull(dao.getUsers());
    }

    @Test
    void getIdByLoginTest() {
        assertEquals(2, dao.getIdByLogin("Anna"));
    }

    @Test
    void saveUserTest() {
        User user = new User("Иван", "Сидоров", "+375-29-334-52-76", "I", "1", Role.CUSTOMER);
       UserEntity userEntity = EntityUtil.convertToUserEntity(user);

        EntityManager em = EMUtil.getEntityManager();
        em.getTransaction().begin();
        em.persist(userEntity);
        em.getTransaction().commit();
        UserEntity userEntityFromDB = em.find(UserEntity.class, userEntity.getId());
        Assertions.assertEquals(userEntity.getLastName(), userEntityFromDB.getLastName());
    }

}
