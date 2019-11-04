package com.jd2.elibrary.dao.impl;

import com.jd2.elibrary.dao.entity.BookEntity;
import com.jd2.elibrary.dao.util.EMUtil;
import com.jd2.elibrary.model.BookGenre;
import org.hibernate.Session;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class DefaultBookDaoTest {
    DefaultBookDao dao = DefaultBookDao.getInstance();

    @Test
    void getBooksTest() {
        assertNotNull(dao.getBooks());
    }

    @Test
    void getById() {
        BookEntity bookEntity = new BookEntity();
        bookEntity.setAuthorFirstName("Герберт");
        bookEntity.setAuthorLastName("Шилдт");
        bookEntity.setIsbn("9785604004364");
        bookEntity.setTitle("Java. Полное руководство. Десятое издание");
        bookEntity.setGenre(BookGenre.SCIENTIFIC);
        bookEntity.setCount(1);
        Session session = EMUtil.getSession();
        session.getTransaction().begin();
        session.save(bookEntity);
        session.getTransaction().commit();

        assertEquals(dao.getById(bookEntity.getId()).getCount(), 1);
    }

    @Test
    void removeBook(){
        BookEntity bookEntity = new BookEntity();
        bookEntity.setAuthorFirstName("Герберт");
        bookEntity.setAuthorLastName("Шилдт");
        bookEntity.setIsbn("9785604004364");
        bookEntity.setTitle("Java. Полное руководство. Десятое издание");
        bookEntity.setGenre(BookGenre.SCIENTIFIC);
        bookEntity.setCount(1);
        Session session = EMUtil.getSession();
        session.getTransaction().begin();
        session.save(bookEntity);
        session.getTransaction().commit();

        dao.removeBook(bookEntity.getId());
        assertEquals(dao.getById(bookEntity.getId()), null);
    }

    @AfterAll
    public static void cleanUp() {
        EMUtil.closeEMFactory();
    }

}
