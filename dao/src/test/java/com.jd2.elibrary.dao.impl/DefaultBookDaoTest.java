package com.jd2.elibrary.dao.impl;

import com.jd2.elibrary.dao.entity.BookEntity;
import com.jd2.elibrary.dao.util.EMUtil;
import com.jd2.elibrary.dao.util.EntityUtil;
import com.jd2.elibrary.model.Book;
import com.jd2.elibrary.model.BookGenre;
import org.hibernate.Session;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class DefaultBookDaoTest {
    DefaultBookDao dao = DefaultBookDao.getInstance();

    public BookEntity bookEntityForTest() {
        BookEntity bookEntity = new BookEntity();
        bookEntity.setAuthorFirstName("Герберт");
        bookEntity.setAuthorLastName("Шилдт");
        bookEntity.setIsbn("9785604004364");
        bookEntity.setTitle("Java. Полное руководство. Десятое издание");
        bookEntity.setGenre(BookGenre.SCIENTIFIC);
        bookEntity.setCount(1);
        return bookEntity;
    }

    @Test
    void getBooksTest() {
        assertNotNull(dao.getBooks(1, 2));
    }

    @Test
    void findBookByIsbn() {
        BookEntity bookEntity = bookEntityForTest();

        final Session session = EMUtil.getSession();
        session.getTransaction().begin();
        session.save(bookEntity);
        session.getTransaction().commit();

        assertTrue(dao.findBookByIsbn("9785604004364"));

        session.getTransaction().begin();
        session.remove(bookEntity);
        session.getTransaction().commit();
    }

    @Test
    void updateCountBookTest() {
        BookEntity bookEntity = bookEntityForTest();

        final Session session = EMUtil.getSession();
        session.getTransaction().begin();
        session.save(bookEntity);
        session.getTransaction().commit();

        Book book = EntityUtil.convertToBook(bookEntity);
        dao.updateCountBook(book, 5);
        assertEquals(5, dao.getById(book.getId()).getCount());

        session.getTransaction().begin();
        session.remove(bookEntity);
        session.getTransaction().commit();
    }

    @Test
    void getByIdTest() {
        BookEntity bookEntity = bookEntityForTest();

        final Session session = EMUtil.getSession();
        session.getTransaction().begin();
        session.save(bookEntity);
        session.getTransaction().commit();

        assertEquals(dao.getById(bookEntity.getId()).getCount(), 1);

        session.getTransaction().begin();
        session.remove(bookEntity);
        session.getTransaction().commit();
    }

    @Test
    void removeBookTest() {
        BookEntity bookEntity = bookEntityForTest();

        final Session session = EMUtil.getSession();
        session.getTransaction().begin();
        session.save(bookEntity);
        session.getTransaction().commit();

        dao.removeBook(bookEntity.getId());
        assertEquals(dao.getById(bookEntity.getId()), null);
    }

}
