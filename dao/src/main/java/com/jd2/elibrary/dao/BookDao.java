package com.jd2.elibrary.dao;

import com.jd2.elibrary.model.Book;

import java.util.List;

public interface BookDao {

        //create
        void addBook(Book book);

        //read
        List<Book> getBooks();
        Book getOrderById(int id);

        //update
        void updateBook(Book book);

        //delete
        void removeBook(Book book);

}
