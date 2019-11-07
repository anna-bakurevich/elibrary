package com.jd2.elibrary.service.impl;

import com.jd2.elibrary.dao.BookDao;
import com.jd2.elibrary.model.Book;
import com.jd2.elibrary.service.impl.impl.DefaultBookService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DefaultBookServiceTest {
    @Mock
    BookDao dao;

    @InjectMocks
    DefaultBookService service;

    @Test
    void getBooksTest() {
        when(dao.getBooks(1, 2)).thenReturn(new ArrayList<Book>());
        List<Book> books = service.getBooks(1,2);
        assertNotNull(books);
    }


    @Test
    void getByIdTest() {
        Book book = new Book();
        book.setId(1);
        when(dao.getById(1)).thenReturn(book);
        assertNotNull(service.getById(1));
        assertNull(service.getById(100));
    }

    @Test
    void saveBookTest() {
        Book book = new Book();
        service.saveBook(book);
        verify(dao).saveBook(book);
    }
}