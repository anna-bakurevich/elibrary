package com.jd2.elibrary.dao.converter;

import com.jd2.elibrary.dao.entity.OrderEntity;
import com.jd2.elibrary.model.Order;
import com.jd2.elibrary.model.OrderStatus;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class OrderConverterTest {
    @Test
    void toOrderNull() {
        final Order order = OrderConverter.convertToOrder(null);
        assertNull(order);
    }

    @Test
    void toOrderEntity() {
        final OrderEntity orderEntity = OrderConverter.convertToOrderEntity(null);
        assertNull(null);
    }

    @Test
    void toOrderNotNull() {
        final OrderEntity orderEntity = new OrderEntity();
        orderEntity.setId(1);
        orderEntity.setOrderStatus(OrderStatus.BLACKLIST);
        orderEntity.setReturnDate(LocalDate.parse("2020-05-05"));

        final Order order = OrderConverter.convertToOrder(orderEntity);
        assertNotNull(order);
        assertEquals(orderEntity.getId(), order.getId());
        assertEquals(orderEntity.getOrderStatus(), order.getOrderStatus());
        assertEquals(orderEntity.getReturnDate(), order.getReturnDate());

    }

    @Test
    void toOrderEntityNotNull() {
        final Order order = new Order();
        order.setId(1);
        order.setOrderStatus(OrderStatus.BLACKLIST);
        order.setReturnDate(LocalDate.parse("2020-05-05"));

        final OrderEntity orderEntity = OrderConverter.convertToOrderEntity(order);
        assertNotNull(orderEntity);
        assertEquals(order.getId(), orderEntity.getId());
        assertEquals(order.getOrderStatus(), orderEntity.getOrderStatus());
        assertEquals(order.getReturnDate(), orderEntity.getReturnDate());
    }
}
