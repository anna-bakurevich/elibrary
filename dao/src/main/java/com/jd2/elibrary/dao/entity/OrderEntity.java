package com.jd2.elibrary.dao.entity;

import com.jd2.elibrary.model.OrderStatus;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "orders_list")
public class OrderEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "order_date")

    private LocalDate orderDate;
    @Column(name = "return_date")

    private LocalDate returnDate;
    @Column(name = "status")
    @Enumerated(value = EnumType.STRING)
    private OrderStatus orderStatus;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private UserEntity userEntity;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "order_book",
            joinColumns = @JoinColumn(name = "order_id"),
            inverseJoinColumns = @JoinColumn(name = "book_id"))
    private List<BookEntity> booksInOrder = new ArrayList<>();

    public OrderEntity() {
    }

    public OrderEntity(LocalDate orderDate, LocalDate returnDate, OrderStatus orderStatus,
                       UserEntity userEntity, List<BookEntity> booksInOrder) {
        this.orderDate = orderDate;
        this.returnDate = returnDate;
        this.orderStatus = orderStatus;
        this.userEntity = userEntity;
        this.booksInOrder = booksInOrder;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public UserEntity getUserEntity() {
        return userEntity;
    }

    public void setUserEntity(UserEntity userEntity) {
        this.userEntity = userEntity;
    }

    public List<BookEntity> getBooksInOrder() {
        return booksInOrder;
    }

    public void setBooksInOrder(List<BookEntity> booksInOrder) {
        this.booksInOrder = booksInOrder;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }
}
