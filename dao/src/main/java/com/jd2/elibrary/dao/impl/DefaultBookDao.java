package com.jd2.elibrary.dao.impl;

import com.jd2.elibrary.dao.BookDao;
import com.jd2.elibrary.dao.entity.BookEntity;
import com.jd2.elibrary.dao.util.EMUtil;
import com.jd2.elibrary.dao.util.EntityUtil;
import com.jd2.elibrary.model.Book;
import org.hibernate.Session;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

public class DefaultBookDao implements BookDao {

    private static DefaultBookDao instance;

    public static synchronized DefaultBookDao getInstance() {
        if (instance == null) {
            instance = new DefaultBookDao();
        }
        return instance;
    }

    @Override
    public void saveBook(Book book) {
        BookEntity bookEntity = EntityUtil.convertToBookEntity(book);

        EntityManager em = EMUtil.getEntityManager();
        em.getTransaction().begin();
        em.persist(bookEntity);
        em.getTransaction().commit();
    }

    @Override
    public List<Book> getBooks() {
       final Session session = EMUtil.getSession();
        Query query = session.createQuery("from BookEntity");
        return query.getResultList();
    }

    @Override
    public Book getById(int id) {
        final EntityManager em = EMUtil.getEntityManager();
        BookEntity bookEntity = em.find(BookEntity.class, id);
        if(bookEntity != null) {
            return EntityUtil.convertToBook(bookEntity);
        }
        return null;

    }

    @Override
    public boolean findBookByIsbn(String isbn) {
       final Session session = EMUtil.getSession();
        Query query = session.createQuery("from BookEntity be where be.isbn = isbn");
        List<BookEntity> booksEntity = query.getResultList();
        if (booksEntity != null) {
            return true;
        }
        return false;
    }

    @Override
    public void updateCountBook(Book book, int count) {
        final Session session = EMUtil.getSession();
        session.beginTransaction();
        session.createQuery("update BookEntity as be set be.count = :count where id = :id")
                .setParameter("id", book.getId())
                .setParameter("count", count)
                .executeUpdate();
        session.getTransaction().commit();
    }

    @Override
    public void removeBook(int id) {
        final Session session = EMUtil.getSession();
        session.getTransaction().begin();
        BookEntity bookEntity = session.find(BookEntity.class, id);
        session.createQuery("delete from BookEntity be where be.id = :id")
                .setParameter("id", bookEntity.getId())
                .executeUpdate();
        session.getTransaction().commit();
    }
}
