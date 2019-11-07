package com.jd2.elibrary.service.impl;

import com.jd2.elibrary.model.Book;

import java.util.List;

public interface BookService {
    List<Book> getBooks(int pageNumber, int pageSize);
    Book getById(int id);
    void saveBook(Book book);
    void decreaseCountBook(int id, int count);
    void increaseCountBook(int id, int count);
    int countPageBooks(int pageSize);
}
