package com.jd2.elibrary.dao.impl;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DefaultOrderDaoTest {
    DefaultOrderDao dao = DefaultOrderDao.getInstance();

    @Test
    void getOrderFilledByUserId() {
        assertEquals(2, dao.getOrderFilledByUserId(4).getId());
    }
}


