package com.jd2.elibrary.service.impl;

import com.jd2.elibrary.dao.OrderDao;
import com.jd2.elibrary.model.Book;
import com.jd2.elibrary.model.Order;
import com.jd2.elibrary.model.OrderStatus;
import com.jd2.elibrary.model.User;
import com.jd2.elibrary.service.impl.impl.DefaultOrderService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class DefaultOrderServiceTest {
    @Mock
    OrderDao dao;

    @InjectMocks
    DefaultOrderService service;

    @Test
    void findAllTest() {
        when(dao.findAll()).thenReturn(new ArrayList<Order>());
        List<Order> orders = service.findAll();
        assertNotNull(orders);
    }

    @Test
    void getBooksByOrderIdTest() {
        when(dao.getBooksByOrderId(1)).thenReturn(new ArrayList<Book>());
        List<Book> books = service.getBooksByOrderId(1);
        assertNotNull(books);
        assertEquals(0, books.size());
    }

    @Test
    void orderIsExistTest() {
        when(dao.existsById(1)).thenReturn(true);
        assertTrue(service.existById(1));
        assertFalse(service.existById(2));
    }

    @Test
    void findAllByUserIdTest() {
        when(dao.findAllByUserId(1)).thenReturn(new ArrayList<Order>());
        List<Order> orders = service.findAllByUserId(1);
        assertNotNull(orders);
    }

    @Test
    void getOrderFilledByUserIdTest() {
        Order orderFilled = new Order();
        orderFilled.setOrderStatus(OrderStatus.FILLED);
        List<Order> orders = Arrays.asList(orderFilled);
        when(dao.findOrderByOrderStatusAndUser(OrderStatus.FILLED, 1)).thenReturn(orders);
        Order order = service.findOrderFilledByUserId(1);
        assertNotNull(order);
        assertEquals(OrderStatus.FILLED, order.getOrderStatus());
    }

    @Test
    void updateTest() {
        Order order = new Order(100, new User(), null, null, OrderStatus.FILLED);
        service.update(order, 1);
        verify(dao).updateOrder(order, 1);
    }

    @Test
    void saveOrderTest() {
        Order order = new Order(100, new User(), null, null, OrderStatus.FILLED);
        service.save(order);
        verify(dao).save(order);
    }
}
