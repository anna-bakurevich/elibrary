package com.jd2.elibrary.service.impl;

import com.jd2.elibrary.dao.BookDao;
import com.jd2.elibrary.model.Book;
import com.jd2.elibrary.service.impl.impl.DefaultBookService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

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

//    @Test
//    void getBooksTest() {
//        when(dao.findAll()).thenReturn(new ArrayList<Book>());
//        List<Book> books = service.paging(1,2);
//        assertNotNull(books);
//    }


    @Test
    void getByIdTest() {
        Book book = new Book();
        book.setId(1);
        when(dao.findById(1)).thenReturn(book);
        assertNotNull(service.findById(1));
        assertNull(service.findById(100));
    }

    @Test
    void saveBookTest() {
        Book book = new Book();
        service.save(book);
        verify(dao).save(book);
    }

//    @Test
//    void incrCountBookTest(){
//        Book book = new Book();
//        book.setCount(5);
//        System.out.println(book.getCount());
//        when(dao.findById(book.getId())).thenReturn(book);
//        service.incrCountBook(book.getId(),1);
//        verify(dao).updateCount(any(), eq(6));
//    }

//    @Test
//    void decrCountBookTest(){
//        Book book = new Book();
//        book.setCount(5);
//        when(dao.findById(1)).thenReturn(book);
//        service.decrCountBook(1,1);
//        verify(dao).updateCount(any(), eq(4));
//    }

    @Test
    void countPageBooksTest(){
    }
}