package com.jd2.elibrary.service.impl;

import com.jd2.elibrary.model.Book;

import java.util.List;

public interface BookService {
    List<Book> paging (int pageNumber, int size);
    Book findById(int id);
    void save(Book book);
    void decrCountBook(int id, int count);
    void incrCountBook(int id, int count);
    int countPageBooks(int pageSize);
}
