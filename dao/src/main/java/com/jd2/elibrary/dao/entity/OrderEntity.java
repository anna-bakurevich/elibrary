package com.jd2.elibrary.dao.entity;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "orders_list")
public class OrderEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "user_id")
    private int userId;
    @Column(name = "order_date")
    private LocalDate orderDate;
    @Column(name = "return_date")
    private LocalDate returnDate;

    public OrderEntity() {

    }

    public OrderEntity(int userId, LocalDate orderDate, LocalDate returnDate) {
        this.userId = userId;
        this.orderDate = orderDate;
        this.returnDate = returnDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDate orderDate) {
        this.orderDate = orderDate;
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
    }
}
