package com.jd2.elibrary.service.impl.impl;

import com.jd2.elibrary.dao.OrderDao;
import com.jd2.elibrary.dao.impl.DefaultOrderDao;
import com.jd2.elibrary.model.Order;
import com.jd2.elibrary.service.impl.OrderService;

import java.util.List;

public class DefaultOrderService implements OrderService {
    private OrderDao orderDao = DefaultOrderDao.getInstance();

    private static DefaultOrderService instance;

    public static synchronized DefaultOrderService getInstance() {
        if (instance == null) {
            instance = new DefaultOrderService();
        }
        return instance;
    }


    @Override
    public List<Order> getOrders() {
        return orderDao.getOrders();
    }

    @Override
    public boolean orderIsExist(int userId) {
        if (orderDao.findById(userId)) {
            return true;
        }
        return false;
    }

    @Override
    public Order getOrderByUserId(int userId) {
        return orderDao.getOrderByUserId(userId);
    }

    @Override
    public void saveOrder(Order order) {
        orderDao.saveOrder(order);
    }


}
