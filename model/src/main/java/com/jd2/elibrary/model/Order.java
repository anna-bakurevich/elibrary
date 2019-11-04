package com.jd2.elibrary.model;

import java.time.LocalDate;

public class Order {
    private int id;
    private User user;
    private LocalDate orderDate;
    private LocalDate returnDate;
    private OrderStatus orderStatus;

    public Order(){
    }

    public Order(int id, User user, LocalDate orderDate, LocalDate returnDate, OrderStatus orderStatus) {
        this.id = id;
        this.user = user;
        this.orderDate = orderDate;
        this.returnDate = returnDate;
        this.orderStatus = orderStatus;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setOrderDate(LocalDate orderDate) {
        this.orderDate = orderDate;
    }

    public void setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }

    public int getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }
}
