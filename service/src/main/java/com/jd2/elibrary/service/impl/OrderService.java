package com.jd2.elibrary.service.impl;

import com.jd2.elibrary.model.Book;
import com.jd2.elibrary.model.Order;

import java.util.List;

public interface OrderService {
    List<Order> getOrders();
    List<Book> getBooksByOrderId(int orderId);

    boolean orderIsExist(int userId);

    void saveOrder(Order order);

    void updateOrder(Order order, int bookId);

    List<Order> getOrderByUserId(int userId);

    Order getOrderFilledByUserId(int userId);
}
