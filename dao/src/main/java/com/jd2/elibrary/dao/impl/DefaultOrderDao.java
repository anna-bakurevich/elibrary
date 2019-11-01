package com.jd2.elibrary.dao.impl;

import com.jd2.elibrary.dao.OrderDao;
import com.jd2.elibrary.dao.entity.OrderEntity;
import com.jd2.elibrary.dao.util.EMUtil;
import com.jd2.elibrary.dao.util.EntityUtil;
import com.jd2.elibrary.model.Order;
import org.hibernate.Session;
import org.hibernate.query.Query;

import javax.persistence.EntityManager;
import java.util.List;

public class DefaultOrderDao implements OrderDao {
    private static DefaultOrderDao instance;

    public static synchronized DefaultOrderDao getInstance() {
        if (instance == null) {
            instance = new DefaultOrderDao();
        }
        return instance;
    }
    @Override
    public void saveOrder(Order order) {
        OrderEntity orderEntity = EntityUtil.convertToOrderEntity(order);

        EntityManager em = EMUtil.getEntityManager();
        em.getTransaction().begin();
        em.persist(orderEntity);
        em.getTransaction().commit();
    }

    @Override
    public List<Order> getOrders() {
        return null;
    }

    @Override
    public Order getOrderById(int id) {
        EntityManager em = EMUtil.getEntityManager();
        OrderEntity orderEntity = em.find(OrderEntity.class, id);
        return EntityUtil.convertToOrder(orderEntity);
    }

    @Override
    public Order getOrderByUserId(int userId) {
        Session session = EMUtil.getSession();
        Query query = session.createQuery("from OrderEntity oe where oe.userEntity.id = :userId");
        query.setParameter("userId", userId);
        return EntityUtil.convertToOrder((OrderEntity)query.uniqueResult());
    }

   //добавление книги в существующий заказ
    @Override
    public void updateOrder(Order order, int bookId) {

    }

    @Override
    public void removeOrder(Order order) {

    }

    @Override
    public boolean findById(int id) {
        EntityManager em = EMUtil.getEntityManager();
        if (em.find(OrderEntity.class, id) != null) {
            return true;
        }
        return false;
    }
}
