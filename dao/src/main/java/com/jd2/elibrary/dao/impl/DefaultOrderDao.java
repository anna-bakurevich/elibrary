package com.jd2.elibrary.dao.impl;

import com.jd2.elibrary.dao.OrderDao;
import com.jd2.elibrary.dao.converter.BookConverter;
import com.jd2.elibrary.dao.converter.OrderConverter;
import com.jd2.elibrary.dao.converter.UserConverter;
import com.jd2.elibrary.dao.entity.BookEntity;
import com.jd2.elibrary.dao.entity.OrderEntity;
import com.jd2.elibrary.dao.entity.UserEntity;
import com.jd2.elibrary.dao.repository.OrderJpaRepository;
import com.jd2.elibrary.dao.repository.UserJpaRepository;
import com.jd2.elibrary.model.Book;
import com.jd2.elibrary.model.Order;
import com.jd2.elibrary.model.OrderStatus;
import com.jd2.elibrary.model.User;

import java.util.List;

public class DefaultOrderDao implements OrderDao {
    private final OrderJpaRepository orderJpaRepository;
    private final UserJpaRepository userJpaRepository;

    public DefaultOrderDao(OrderJpaRepository orderJpaRepository, UserJpaRepository userJpaRepository) {
        this.orderJpaRepository = orderJpaRepository;
        this.userJpaRepository = userJpaRepository;
    }

    @Override
    public void save(Order order) {
        OrderEntity orderEntity = OrderConverter.convertToOrderEntity(order);
        orderJpaRepository.save(orderEntity);
    }

    @Override
    public Order findByOrderStatus(OrderStatus orderStatus) {
        OrderEntity orderEntity = orderJpaRepository.findByOrderStatus(orderStatus);
        return OrderConverter.convertToOrder(orderEntity);
    }

    @Override
    public List<Order> findAll() {
        List<OrderEntity> orderEntities = orderJpaRepository.findAll();
        return OrderConverter.convertToListOrder(orderEntities);
    }

    @Override
    public List<Order> findAllByUserId(int userId) {
        UserEntity userEntity = userJpaRepository.findById(userId).get();
        List<OrderEntity> orderEntities = orderJpaRepository.findAllByUserEntity(userEntity);
        return OrderConverter.convertToListOrder(orderEntities);
    }

    @Override
    public List<Order> findOrderByOrderStatusAndUser(OrderStatus orderStatus, int userId) {
        UserEntity userEntity = userJpaRepository.findById(userId).get();
        List<OrderEntity> orderEntities = orderJpaRepository.findOrderEntitiesByOrderStatusAndUserEntity(
                orderStatus, userEntity);
        return OrderConverter.convertToListOrder(orderEntities);
    }

    @Override
    public Order findById(int id) {
        OrderEntity orderEntity = orderJpaRepository.findById(id).get();
        return OrderConverter.convertToOrder(orderEntity);
    }

    @Override
    public List<Book> getBooksByOrderId(int orderId) {
        OrderEntity orderEntity = orderJpaRepository.findById(orderId).get();
        List<BookEntity> bookEntities = orderEntity.getBooksInOrder();
        return BookConverter.convertToListBook(bookEntities);
    }

    @Override
    public boolean existsById(int id) {
        return orderJpaRepository.existsById(id);
    }

    @Override
    public boolean existByUser(User user) {
        UserEntity userEntity = UserConverter.convertToUserEntity(user);
        return orderJpaRepository.existsByUserEntity(userEntity);
    }

    @Override
    public void updateOrder(Order order, int bookId) {

    }

    @Override
    public void deleteById(int id) {
        orderJpaRepository.deleteById(id);
    }
}
