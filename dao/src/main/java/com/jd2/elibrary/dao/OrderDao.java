package com.jd2.elibrary.dao;

import com.jd2.elibrary.model.Order;

import java.util.List;

public interface OrderDao {
    //create
    void saveOrder(Order order);

    //read
    List<Order> getOrders();
    Order getOrderById(int id);
    Order getOrderByUserId(int userId);

    //update
    void updateOrder(Order order, int bookId);

    //delete
    void removeOrder(Order order);

    boolean findById(int id);
}
