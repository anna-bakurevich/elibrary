package com.jd2.elibrary.dao;

import com.jd2.elibrary.model.Book;

import java.util.List;

public interface BookDao {

    //create
    void save(Book book);

    //read
    int count();

    List<Book> findAll();

    List<Book> paging(int pageNumber, int size);

    Book findById(int id);

    Book findByIsbn(String isbn);

    //update
    void updateCount(int id, int count);

    //delete
    void deleteById(int id);
}
