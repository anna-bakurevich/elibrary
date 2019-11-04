package com.jd2.elibrary.dao.impl;

import com.jd2.elibrary.dao.OrderDao;
import com.jd2.elibrary.dao.entity.BookEntity;
import com.jd2.elibrary.dao.entity.OrderEntity;
import com.jd2.elibrary.dao.util.EMUtil;
import com.jd2.elibrary.dao.util.EntityUtil;
import com.jd2.elibrary.model.Book;
import com.jd2.elibrary.model.Order;
import org.hibernate.Session;
import org.hibernate.query.Query;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

import static com.jd2.elibrary.model.OrderStatus.FILLED;

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
    public List<Book> getBooksByOrderId(int orderId) {
        OrderEntity orderEntity = EntityUtil.convertToOrderEntity(getOrderById(orderId));
        List<BookEntity> booksEntity = orderEntity.getBooksInOrder();
        List<Book> books = EntityUtil.convertListToBook(booksEntity);
        return books;
    }

    @Override
    public List<Order> getOrdersByUserId(int userId) {
        Session session = EMUtil.getSession();
        Query query = session.createQuery("from OrderEntity oe where oe.userEntity.id = :userId");
        query.setParameter("userId", userId);
        return query.getResultList();
    }

    @Override
    public Order getOrderFilledByUserId(int userId) {
        EntityManager em = EMUtil.getEntityManager();
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<OrderEntity> criteria = cb.createQuery(OrderEntity.class);
        Root<OrderEntity> order = criteria.from(OrderEntity.class);
        Predicate predicate = cb.and(
                cb.equal(order.get("userEntity"), userId),
                cb.equal(order.get("orderStatus"), FILLED)
        );
        criteria.select(order).where(predicate);

        List<OrderEntity> orders = em.createQuery(criteria).getResultList();
        if (orders != null) {
            Order orderFilled = EntityUtil.convertToOrder(orders.get(0));
            return orderFilled;
        }
        return null;
    }

    //добавление книги в существующий заказ
    @Override
    public void updateOrder(Order order, int bookId) {
        EntityManager em = EMUtil.getEntityManager();
        em.getTransaction().begin();
        OrderEntity orderEntity = em.find(OrderEntity.class, order.getId());
        BookEntity bookEntity = em.find(BookEntity.class, bookId);
        orderEntity.getBooksInOrder().add(bookEntity);
        em.getTransaction().commit();
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