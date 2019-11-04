package com.jd2.elibrary.dao.impl;

import com.jd2.elibrary.dao.entity.BookEntity;
import com.jd2.elibrary.dao.util.EMUtil;
import com.jd2.elibrary.model.BookGenre;
import org.hibernate.Session;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class DefaultBookDaoTest {
    DefaultBookDao dao = DefaultBookDao.getInstance();

    @BeforeAll
    public static void init() {
        BookEntity bookEntity = new BookEntity();
        bookEntity.setId(18);
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
    }

    @Test
    void getBooksTest() {
        assertNotNull(dao.getBooks());
    }

    @Test
    void getById() {
        assertEquals(dao.getById(18).getCount(), 1);
    }

    @Test
    void removeBook(){
        dao.removeBook(18);
        assertEquals(dao.getById(18), null);
    }

    @AfterAll
    public static void cleanUp() {
        EMUtil.closeEMFactory();
    }

}
