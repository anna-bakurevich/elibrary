package com.jd2.elibrary.dao.impl;

import org.junit.jupiter.api.Test;

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
    void getIdByLoginTest(){
        assertEquals(1, dao.getIdByLogin("Anna"));
    }


}
