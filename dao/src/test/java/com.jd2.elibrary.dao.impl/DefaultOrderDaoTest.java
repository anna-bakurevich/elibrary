package com.jd2.elibrary.dao.impl;

import com.jd2.elibrary.dao.converter.OrderConverter;
import com.jd2.elibrary.dao.entity.BookEntity;
import com.jd2.elibrary.dao.entity.OrderEntity;
import com.jd2.elibrary.dao.entity.UserEntity;
import com.jd2.elibrary.dao.util.EMUtil;
import com.jd2.elibrary.model.Order;
import com.jd2.elibrary.model.OrderStatus;
import org.hibernate.Session;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class DefaultOrderDaoTest {
    DefaultOrderDao dao = new DefaultOrderDao();

    public OrderEntity orderEntityForTest() {
        List<OrderEntity> orders = new ArrayList<>();
        UserEntity userEntity = new UserEntity(null, "Anna", null, null,
                null, null, orders);
        userEntity.setId(4);
        List<BookEntity> booksEntity = new ArrayList<>();
        OrderEntity orderEntity = new OrderEntity();
        orderEntity.setOrderDate(LocalDate.now());
        orderEntity.setReturnDate(LocalDate.now().plusDays(30));
        orderEntity.setUserEntity(userEntity);
        orderEntity.setOrderStatus(OrderStatus.FILLED);
        orderEntity.setBooksInOrder(booksEntity);
        return orderEntity;
    }

    @Test
    void saveOrderTest() {
        OrderEntity orderEntity = orderEntityForTest();
        Order order = OrderConverter.convertToOrder(orderEntity);

        dao.saveOrder(order);

        assertNotNull(dao.getOrderFilledByUserId(4));

        final Session session = EMUtil.getSession();
        session.getTransaction().begin();
        session.remove(orderEntity);
        session.getTransaction().commit();
    }

    @Test
    void getOrdersTest() {
        OrderEntity orderEntity = orderEntityForTest();

        final Session session = EMUtil.getSession();
        session.getTransaction().begin();
        session.save(orderEntity);
        session.getTransaction().commit();

        assertNull(dao.getOrders());

        session.getTransaction().begin();
        session.remove(orderEntity);
        session.getTransaction().commit();
    }

    @Test
    void getOrderByIdTest() {
        OrderEntity orderEntity = orderEntityForTest();

        final Session session = EMUtil.getSession();
        session.getTransaction().begin();
        session.save(orderEntity);
        session.getTransaction().commit();

        assertNotNull(dao.getOrderById(orderEntity.getId()));

        session.getTransaction().begin();
        session.remove(orderEntity);
        session.getTransaction().commit();
    }

    @Test
    void getBooksByOrderIdTest() {
        OrderEntity orderEntity = orderEntityForTest();

        final Session session = EMUtil.getSession();
        session.getTransaction().begin();
        session.save(orderEntity);
        session.getTransaction().commit();

        assertNotNull(dao.getBooksByOrderId(orderEntity.getId()));

        session.getTransaction().begin();
        session.remove(orderEntity);
        session.getTransaction().commit();
    }

    @Test
    void getOrdersByUserIdTest() {
        OrderEntity orderEntity = orderEntityForTest();

        final Session session = EMUtil.getSession();
        session.getTransaction().begin();
        session.save(orderEntity);
        session.getTransaction().commit();

        assertNotNull(dao.getOrdersByUserId(4));

        session.getTransaction().begin();
        session.remove(orderEntity);
        session.getTransaction().commit();
    }

    @Test
    void getOrderFilledByUserIdTest() {
        OrderEntity orderEntity = orderEntityForTest();

        final Session session = EMUtil.getSession();
        session.getTransaction().begin();
        session.save(orderEntity);
        session.getTransaction().commit();

        assertNotNull(dao.getOrderFilledByUserId(4));

        session.getTransaction().begin();
        session.remove(orderEntity);
        session.getTransaction().commit();
    }

    @Test
    void findByIdTest() {
        OrderEntity orderEntity = orderEntityForTest();

        final Session session = EMUtil.getSession();
        session.getTransaction().begin();
        session.save(orderEntity);
        session.getTransaction().commit();

        assertTrue(dao.findById(orderEntity.getId()));
        assertFalse(dao.findById(1000));

        session.getTransaction().begin();
        session.remove(orderEntity);
        session.getTransaction().commit();
    }
}


