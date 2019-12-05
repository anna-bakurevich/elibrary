package com.jd2.elibrary.dao.repository;

import com.jd2.elibrary.dao.entity.OrderEntity;
import com.jd2.elibrary.dao.entity.UserEntity;
import com.jd2.elibrary.model.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderJpaRepository extends JpaRepository<OrderEntity, Integer> {
    OrderEntity findByOrderStatus(OrderStatus orderStatus);
    List<OrderEntity> findAllByUserEntity(UserEntity userEntity);
    List<OrderEntity> findOrderEntitiesByOrderStatus(OrderStatus orderStatus);
    List<OrderEntity> findOrderEntitiesByOrderStatusAndUserEntity(OrderStatus orderStatus, UserEntity userEntity);
    boolean existsByUserEntity(UserEntity userEntity);
//    void updateCount(OrderEntity orderEntity, int bookId);
}
