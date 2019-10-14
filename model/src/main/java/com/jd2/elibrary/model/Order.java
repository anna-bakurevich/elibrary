package com.jd2.elibrary.model;

import java.time.LocalDate;

public class Order {
    private int id;
    private LocalDate ordersDate;
    private LocalDate returnDate;

    public LocalDate getOrdersDate() {
        return ordersDate;
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }

    public int getId() {
        return id;
    }
}
