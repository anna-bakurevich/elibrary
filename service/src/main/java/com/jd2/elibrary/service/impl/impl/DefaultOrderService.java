package com.jd2.elibrary.service.impl.impl;

import com.jd2.elibrary.dao.OrderDao;
import com.jd2.elibrary.model.Book;
import com.jd2.elibrary.model.Order;
import com.jd2.elibrary.model.OrderStatus;
import com.jd2.elibrary.model.User;
import com.jd2.elibrary.service.impl.OrderService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class DefaultOrderService implements OrderService {

    private final OrderDao defaultOrderDao;

    public DefaultOrderService(OrderDao defaultOrderDao) {
        this.defaultOrderDao = defaultOrderDao;
    }


    @Override
    @Transactional
    public void save(Order order) {
        defaultOrderDao.save(order);
    }

    @Override
    @Transactional
    public List<Order> findAll() {
        return defaultOrderDao.findAll();
    }

    @Override
    @Transactional
    public void update(Order order, int bookId) {
        defaultOrderDao.updateOrder(order, bookId);
    }

    @Override
    @Transactional
    public List<Book> getBooksByOrderId(int orderId) {
        defaultOrderDao.getBooksByOrderId(orderId);
        return defaultOrderDao.getBooksByOrderId(orderId);
    }

    @Override
    @Transactional
    public boolean existById(int id) {
        return defaultOrderDao.existsById(id);
    }

    @Override
    @Transactional
    public boolean existByUser(User user){
        return defaultOrderDao.existByUser(user);
    }

    @Override
    @Transactional
    public List<Order> findAllByUserId(int userId) {
        return defaultOrderDao.findAllByUserId(userId);
    }

    @Override
    @Transactional
    public Order findOrderFilledByUserId(int userId) {
        return defaultOrderDao.findOrderByOrderStatusAndUser(OrderStatus.FILLED, userId).get(0);
    }


}
