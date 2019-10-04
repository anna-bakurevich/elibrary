package com.jd2.elibrary.dao;

import com.jd2.elibrary.model.Order;

import java.sql.Connection;
import java.util.List;

public interface OrderDao {
    //create
    void addOrder(Order order);

    //read
    List<Order> getOrders();
    Order getOrderById(int id);

    //update
    void updateOrder(Order order);

    //delete
    void removeOrder(Order order);

    Connection connect();
}
