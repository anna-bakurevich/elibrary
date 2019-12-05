package com.jd2.elibrary.dao.converter;

import com.jd2.elibrary.dao.entity.OrderEntity;
import com.jd2.elibrary.model.Order;

import java.util.ArrayList;
import java.util.List;

import static com.jd2.elibrary.dao.converter.UserConverter.convertToUser;
import static com.jd2.elibrary.dao.converter.UserConverter.convertToUserEntity;

public class OrderConverter {
    public static OrderEntity convertToOrderEntity(Order order) {
        if (order == null) {
            return null;
        }
        final OrderEntity orderEntity = new OrderEntity();
        orderEntity.setId(order.getId());
        orderEntity.setUserEntity(convertToUserEntity(order.getUser()));
        orderEntity.setOrderDate(order.getOrderDate());
        orderEntity.setReturnDate(order.getReturnDate());
        orderEntity.setOrderStatus(order.getOrderStatus());
        return orderEntity;
    }

    public static Order convertToOrder(OrderEntity orderEntity) {
        if (orderEntity == null) {
            return null;
        }
        final Order order = new Order();
        order.setId(orderEntity.getId());
        order.setUser(convertToUser(orderEntity.getUserEntity()));
        order.setOrderDate(orderEntity.getOrderDate());
        order.setReturnDate(orderEntity.getReturnDate());
        order.setOrderStatus(orderEntity.getOrderStatus());
        return order;
    }

    public static List<Order> convertToListOrder(List<OrderEntity> orderEntities) {
        if (orderEntities == null){
            return null;
        }
        final List<Order> orders = new ArrayList<>();
        for (OrderEntity oe : orderEntities) {
            orders.add(convertToOrder(oe));
        }
        return orders;
    }
}
