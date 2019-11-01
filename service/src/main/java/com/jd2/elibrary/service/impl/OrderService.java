package com.jd2.elibrary.service.impl;

import com.jd2.elibrary.model.Order;

import java.util.List;

public interface OrderService {
    List<Order> getOrders();
    boolean orderIsExist(int userId);
    void saveOrder(Order order);
    Order getOrderByUserId(int userId);
}
