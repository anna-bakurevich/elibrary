package com.jd2.elibrary.dao;

import com.jd2.elibrary.model.Order;

import java.util.List;

public interface OrderDao {
    //create
    void saveOrder(Order order);

    //read
    List<Order> getOrders();
    Order getOrderById(int id);

    //update
    void updateOrder(Order order);

    //delete
    void removeOrder(Order order);

}
