package com.jd2.elibrary.dao.impl;

import com.jd2.elibrary.dao.converter.UserConverter;
import com.jd2.elibrary.dao.entity.UserEntity;
import com.jd2.elibrary.dao.util.EMUtil;
import com.jd2.elibrary.model.Role;
import com.jd2.elibrary.model.User;
import org.hibernate.Session;
import org.junit.jupiter.api.Test;

import javax.persistence.EntityManager;

import static org.junit.jupiter.api.Assertions.*;


public class DefaultUserDaoTest {
    DefaultUserDao dao = new DefaultUserDao();

    public UserEntity userEntityForTest() {

        UserEntity userEntity = new UserEntity();
        userEntity.setFirstName("Иван");
        userEntity.setLastName("Сидоров");
        userEntity.setPhone("+375-29-334-52-76");
        userEntity.setLogin("I");
        userEntity.setPassword("1");
        userEntity.setRole(Role.CUSTOMER);
        return userEntity;
    }

    @Test
    void saveUserTest() {
        UserEntity userEntity = userEntityForTest();
        User user = UserConverter.convertToUser(userEntity);

        final int id = dao.saveUser(user);

        Session session = EMUtil.getSession();
        UserEntity userEntityFromDB = session.get(UserEntity.class, id);

        assertNotNull(userEntityFromDB);
        assertEquals(userEntity.getLastName(), userEntityFromDB.getLastName());


        session.getTransaction().begin();
        session.remove(userEntityFromDB);
        session.getTransaction().commit();
}

    @Test
    void removeUserTest() {
        UserEntity userEntity = userEntityForTest();

        final Session session = EMUtil.getSession();
        session.getTransaction().begin();
        session.save(userEntity);
        session.getTransaction().commit();

        dao.removeUser(userEntity.getId());

       final EntityManager em = EMUtil.getEntityManager();
        UserEntity userEntityFromDB = em.find(UserEntity.class, userEntity.getId());
        assertEquals(null, userEntityFromDB);
    }

    @Test
    void findById() {
        UserEntity userEntity = userEntityForTest();

        final Session session = EMUtil.getSession();
        session.getTransaction().begin();
        session.save(userEntity);
        session.getTransaction().commit();

        assertNotNull(dao.findById(dao.getIdByLogin("I")));
        assertTrue(dao.findById(dao.getIdByLogin("I")));
        assertFalse(dao.findById(1000));

        session.getTransaction().begin();
        session.remove(userEntity);
        session.getTransaction().commit();

    }

    @Test
    void getUsersTest() {

        assertNotNull(dao.getUsers());
    }

    @Test
    void getIdByLoginTest() {
        UserEntity userEntity = userEntityForTest();

       final Session session = EMUtil.getSession();
        session.getTransaction().begin();
        session.save(userEntity);
        session.getTransaction().commit();

        int id = userEntity.getId();
        assertNotNull(dao.getIdByLogin("I"));
        assertEquals(id, dao.getIdByLogin("I"));

        session.getTransaction().begin();
        session.remove(userEntity);
        session.getTransaction().commit();
    }

    @Test
    void getByIdTest() {
        UserEntity userEntity = userEntityForTest();

       final Session session = EMUtil.getSession();
        session.getTransaction().begin();
        session.save(userEntity);
        session.getTransaction().commit();
        User userFromDB = dao.getById(userEntity.getId());

        assertNotNull(userFromDB);
        assertEquals(userEntity.getId(), userFromDB.getId());
        assertEquals("Иван", userFromDB.getFirstName());


        session.getTransaction().begin();
        session.remove(userEntity);
        session.getTransaction().commit();
    }

    @Test
    void getByLoginTest(){
        UserEntity userEntity = userEntityForTest();

        final Session session = EMUtil.getSession();
        session.getTransaction().begin();
        session.save(userEntity);
        session.getTransaction().commit();
        User userFromDB = dao.getByLogin(userEntity.getLogin());

        assertNotNull(userFromDB);
        assertEquals("Иван", userFromDB.getFirstName());


        session.getTransaction().begin();
        session.remove(userEntity);
        session.getTransaction().commit();
    }


//    @AfterAll
//    public static void cleanUp() {
//        EMUtil.closeEMFactory();
//    }
}
