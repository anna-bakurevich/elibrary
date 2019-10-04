package com.jd2.elibrary.dao;

import com.jd2.elibrary.model.OrderSpecification;

import java.sql.Connection;
import java.util.List;

public interface OrderSpecificationDao {
    //create
    void addOrderSpecification(OrderSpecification orderSpecification);

    //read
    List<OrderSpecification> getOrdersSpecification();

    OrderSpecification getOrderSpecificationById(int id);

    //update
    void updateOrderSpecification(OrderSpecification orderSpecification);

    //delete
    void removeOrderSpecification(OrderSpecification orderSpecification);

    Connection connect();
}

