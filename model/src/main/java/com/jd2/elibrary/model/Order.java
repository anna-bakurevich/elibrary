package com.jd2.elibrary.model;

import java.time.LocalDate;

public class Order {
    private int id;
    private int userId;
    private LocalDate orderDate;

    public void setId(int id) {
        this.id = id;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setOrderDate(LocalDate orderDate) {
        this.orderDate = orderDate;
    }

    public void setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
    }

    private LocalDate returnDate;

    public LocalDate getOrderDate() {
        return orderDate;
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }

    public int getId() {
        return id;
    }

    public int getUserId() {
        return userId;
    }
}
