package com.jd2.elibrary.dao.entity;

import javax.persistence.*;

@Entity
@Table(name = "order_specification")
public class OrderSpecificationEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "order_id")
    private int orderId;
    @Column(name = "book_id")
    private int bookId;

    @ManyToOne
    @JoinColumn(name = "id")
    private OrderEntity orderEntity;

    @ManyToOne
    @JoinColumn(name = "id")
    private BookEntity bookEntity;

    public OrderSpecificationEntity(){

    }

    public OrderSpecificationEntity(int orderId, int bookId, OrderEntity orderEntity, BookEntity bookEntity) {
        this.orderId = orderId;
        this.bookId = bookId;
        this.orderEntity = orderEntity;
        this.bookEntity = bookEntity;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public OrderEntity getOrderEntity() {
        return orderEntity;
    }

    public void setOrderEntity(OrderEntity orderEntity) {
        this.orderEntity = orderEntity;
    }

    public BookEntity getBookEntity() {
        return bookEntity;
    }

    public void setBookEntity(BookEntity bookEntity) {
        this.bookEntity = bookEntity;
    }
}
