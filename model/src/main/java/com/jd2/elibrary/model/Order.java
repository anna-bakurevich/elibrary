package com.jd2.elibrary.model;

import java.time.LocalDate;

public class Order {
    private int id;
    private int userId;
    private LocalDate orderDate;
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
