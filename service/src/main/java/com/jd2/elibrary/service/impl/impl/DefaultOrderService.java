package com.jd2.elibrary.service.impl.impl;

import com.jd2.elibrary.dao.OrderDao;
import com.jd2.elibrary.model.Book;
import com.jd2.elibrary.model.Order;
import com.jd2.elibrary.service.impl.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DefaultOrderService implements OrderService {

    @Autowired
    private OrderDao orderDao;

//            = DefaultOrderDao.getInstance();

//    private static DefaultOrderService instance;
//
//    public static synchronized DefaultOrderService getInstance() {
//        if (instance == null) {
//            instance = new DefaultOrderService();
//        }
//        return instance;
//    }


    @Override
    public List<Order> getOrders() {
        return orderDao.getOrders();
    }

    @Override
    public List<Book> getBooksByOrderId(int orderId) {
        orderDao.getBooksByOrderId(orderId);

        return null;
    }

    @Override
    public boolean orderIsExist(int userId) {
        if (orderDao.findById(userId)) {
            return true;
        }
        return false;
    }

    @Override
    public List<Order> getOrderByUserId(int userId) {
        return orderDao.getOrdersByUserId(userId);
    }

    @Override
    public Order getOrderFilledByUserId(int userId) {
        return orderDao.getOrderFilledByUserId(userId);
    }

    @Override
    public void saveOrder(Order order) {
        orderDao.saveOrder(order);
    }

    @Override
    public void updateOrder(Order order, int bookId) {
        orderDao.updateOrder(order, bookId);
    }


}
