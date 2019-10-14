package com.jd2.elibrary.dao.impl;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class DefaultBookDaoTest {
    DefaultBookDao dao = DefaultBookDao.getInstance();

    @Test
    void getBooksTest() {
        assertNotNull(dao.getBooks());
    }

}
