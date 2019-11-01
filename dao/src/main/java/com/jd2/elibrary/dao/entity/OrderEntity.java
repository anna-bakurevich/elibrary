package com.jd2.elibrary.dao.entity;

import org.hibernate.annotations.CreationTimestamp;

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
    @Column(name = "user_id")
    private int userId;
    @Column(name = "order_date")
    @CreationTimestamp
    private LocalDate orderDate;
    @Column(name = "return_date")
    @CreationTimestamp
    private LocalDate returnDate;


    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id")
    private UserEntity userEntity;

    @OneToMany(mappedBy = "orderEntity", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderSpecificationEntity> specification = new ArrayList<>();

    public UserEntity getUserEntity() {
        return userEntity;
    }

    public void setUserEntity(UserEntity userEntity) {
        this.userEntity = userEntity;
    }

    public OrderEntity() {

    }

    public OrderEntity(int userId, LocalDate orderDate, LocalDate returnDate, UserEntity userEntity,
                       List<OrderSpecificationEntity> specification) {
        this.userId = userId;
        this.orderDate = orderDate;
        this.returnDate = returnDate;
        this.userEntity = userEntity;
        this.specification = specification;
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


    public List<OrderSpecificationEntity> getSpecification() {
        return specification;
    }

    public void setSpecification(List<OrderSpecificationEntity> specification) {
        this.specification = specification;
    }
}
