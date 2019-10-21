package com.jd2.elibrary.service.impl;

import com.jd2.elibrary.dao.BookDao;
import com.jd2.elibrary.model.entity.Book;
import com.jd2.elibrary.service.impl.impl.DefaultBookService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DefaultBookServiceTest {
    @Mock
    BookDao dao;

    @InjectMocks
    DefaultBookService service;

    @Test
    void getBooksTest() {
       when(dao.getBooks()).thenReturn(new ArrayList<Book>());
        List<Book> books = service.getBooks();
        assertNotNull(books);
    }
}