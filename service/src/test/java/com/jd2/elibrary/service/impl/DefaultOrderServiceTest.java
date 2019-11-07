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
    void getOrdersTest() {
        when(dao.getOrders()).thenReturn(new ArrayList<Order>());
        List<Order> orders = service.getOrders();
        assertNotNull(orders);
    }

    @Test
    void getBooksByOrderIdTest() {
        when(dao.getBooksByOrderId(1)).thenReturn(new ArrayList<Book>());
        List<Book> books = service.getBooksByOrderId(1);
        assertNull(books);
    }

    @Test
    void orderIsExistTest() {
        when(dao.findById(1)).thenReturn(true);
        assertTrue(service.orderIsExist(1));
        assertFalse(service.orderIsExist(2));
    }

    @Test
    void getOrderByUserIdTest() {
        when(dao.getOrdersByUserId(1)).thenReturn(new ArrayList<Order>());
        List<Order> orders = service.getOrderByUserId(1);
        assertNotNull(orders);
    }

    @Test
    void getOrderFilledByUserIdTest() {
        when(dao.getOrderFilledByUserId(1)).thenReturn(new Order());
        Order order = service.getOrderFilledByUserId(1);
        assertNotNull(order);
    }

    @Test
    void updateOrderTest() {
        Order order = new Order(100, new User(), null, null, OrderStatus.FILLED);
        service.updateOrder(order, 1);
        verify(dao).updateOrder(order, 1);
    }

    @Test
    void saveOrderTest() {
        Order order = new Order(100, new User(), null, null, OrderStatus.FILLED);
        service.saveOrder(order);
        verify(dao).saveOrder(order);
    }
}
