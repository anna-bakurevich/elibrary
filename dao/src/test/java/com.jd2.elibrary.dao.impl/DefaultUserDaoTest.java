package com.jd2.elibrary.dao.impl;

import com.jd2.elibrary.dao.entity.UserEntity;
import com.jd2.elibrary.dao.util.EMUtil;
import com.jd2.elibrary.dao.util.EntityUtil;
import com.jd2.elibrary.model.Role;
import com.jd2.elibrary.model.User;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import javax.persistence.EntityManager;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class DefaultUserDaoTest {
    DefaultUserDao dao = DefaultUserDao.getInstance();
//    @BeforeAll
//    public void init() {
//        EntityManager em = EMUtil.getEntityManager();
//        em.getTransaction().begin();
//        Department d = new Department("D");
//        em.persist(d);
//        em.persist(new Employee(null, "Yuli", 27, 16000.99, d));
//        em.persist(new Employee(null, "Max", 38, 10000, d));
//        em.persist(new Employee(null, "Paul", 43, 15000, d));
//        em.persist(new Employee(null, "Gleb", 37, 15000, d));
//        em.persist(new Employee(null, "Li", 62, 13099, d));
//        em.persist(new Employee(null, "Yuli", 22, 4500, d));
//        em.persist(new Employee(null, null, 18, 300, d));
//        em.getTransaction().commit();
//        em.clear();
//    }

    @Test
    void getUsersTest() {

        //assertNotNull(dao.getUsers());
    }

    @Test
    void getIdByLoginTest() {
        assertEquals(1, dao.getIdByLogin("Anna"));
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

    @AfterAll
    public static void cleanUp() {
        EMUtil.closeEMFactory();
    }
}
