package com.jd2.elibrary.dao;

import com.jd2.elibrary.model.Book;

import java.util.List;

public interface BookDao {

    //create
    void saveBook(Book book);

    //read
    List<Book> getBooks();

    Book getById(int id);
    boolean findBookByIsbn(String isbn);

    //update
    void updateCountBook(Book book, int count);

    //delete
    void removeBook(int id);
}
