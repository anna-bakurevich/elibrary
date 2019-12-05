package com.jd2.elibrary.dao.impl;

import com.jd2.elibrary.dao.OrderDao;
import com.jd2.elibrary.dao.UserDao;
import com.jd2.elibrary.dao.config.DaoConfig;
import com.jd2.elibrary.model.Order;
import com.jd2.elibrary.model.OrderStatus;
import com.jd2.elibrary.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = DaoConfig.class)
public class DefaultOrderDaoTest {
    @Autowired
    OrderDao orderDao;
    @Autowired
    UserDao userDao;

    @BeforeEach
    public void init() {
        Order order = new Order();
        User user = userDao.findById(4);
        order.setUser(user);
        order.setOrderDate(LocalDate.now());
        order.setReturnDate(LocalDate.now().plusDays(30));
        order.setOrderStatus(OrderStatus.BLACKLIST);

        orderDao.save(order);
    }

    @Transactional
    @Test
    void saveTest() {
        Order order = orderDao.findByOrderStatus(OrderStatus.BLACKLIST);
        orderDao.save(order);
        assertNotNull(order);
        assertEquals(LocalDate.now(), order.getOrderDate());
    }

    @Transactional
    @Test
    void findByOrderStatusTest() {
        Order order = orderDao.findByOrderStatus(OrderStatus.BLACKLIST);
        assertNotNull(order);
        assertEquals(LocalDate.now(), order.getOrderDate());
    }

    @Transactional
    @Test
    void findAllTest() {
        List<Order> orders = orderDao.findAll();
        assertNotNull(orders);
        assertFalse(orders.isEmpty());
        System.out.println(orders);
    }

    @Transactional
    @Test
    void findAllByUserIdTest() {

        List<Order> orders = orderDao.findAllByUserId(4);

        assertNotNull(orders);
        assertFalse(orders.isEmpty());
        assertEquals(2, orders.size());
    }

    @Transactional
    @Test
    void findOrderByOrderStatusAndUserTest() {
        List<Order> ordersFilled = orderDao.findOrderByOrderStatusAndUser(OrderStatus.FILLED, 4);
        System.out.println(ordersFilled.get(0).getUser().getFirstName());

    }

    @Transactional
    @Test
    void findByIdTest() {
        Order order = orderDao.findByOrderStatus(OrderStatus.BLACKLIST);
        int id = order.getId();
        assertEquals(LocalDate.now(), orderDao.findById(id).getOrderDate());
    }

    @Transactional
    @Test
    void existByUser() {
        User user = userDao.findById(4);
        assertTrue(orderDao.existByUser(user));
    }
}
