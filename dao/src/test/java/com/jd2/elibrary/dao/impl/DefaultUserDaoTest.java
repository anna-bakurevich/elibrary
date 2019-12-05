package com.jd2.elibrary.dao.impl;

import com.jd2.elibrary.dao.UserDao;
import com.jd2.elibrary.dao.config.DaoConfig;
import com.jd2.elibrary.model.Role;
import com.jd2.elibrary.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = DaoConfig.class)
public class DefaultUserDaoTest {

    @Autowired
    private UserDao dao;

    @BeforeEach
    public void init() {
        User user = new User();
        user.setLogin("A1a1a1");
        user.setPassword("1");
        user.setFirstName("А");
        user.setLastName("Б");
        user.setPhone("+");
        user.setRole(Role.CUSTOMER);

        dao.save(user);
    }

    @Transactional
    @Test
    void findByLoginTest() {
        User user = dao.findByLogin("A1a1a1");
        assertNotNull(user);
        assertEquals("А", user.getFirstName());
    }

    @Transactional
    @Test
    void existsByIdTest() {
        assertTrue(dao.existsById(dao.findByLogin("A1a1a1").getId()));

    }

    @Transactional
    @Test
    void findByIdTest() {
        int id = dao.findByLogin("A1a1a1").getId();
        User user = dao.findById(id);
        assertNotNull(dao.findById(id));
        assertEquals("Б", user.getLastName());
    }

    @Transactional
    @Test
    void getIdByLoginTest() {
        User user = dao.findByLogin("A1a1a1");
        int id = user.getId();
        assertEquals(id, dao.getIdByLogin("A1a1a1"));
    }


    @Transactional
    @Test
    void deleteByIdTest() {
        User user = dao.findByLogin("A1a1a1");
        dao.deleteById(user.getId());
        assertNull(dao.findByLogin("A1a1a1"));
    }

    @Test
    void updateTest() {
        int id = dao.findByLogin("A1a1a1").getId();
        User user = dao.findById(id);

        dao.update(user, "Б", "В", "+++");

        final User userAfterUpdate = dao.findById(id);
        assertEquals("Б", userAfterUpdate.getFirstName());
        assertEquals("В", userAfterUpdate.getLastName());
        assertEquals("+++", userAfterUpdate.getPhone());

        dao.deleteById(userAfterUpdate.getId());
    }

    @Transactional
    @Test
    void findAll() {
        List<User> users = dao.findAll();
        assertNotNull(users);
    }
}
