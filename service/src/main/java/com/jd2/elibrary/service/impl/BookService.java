package com.jd2.elibrary.service.impl;

import com.jd2.elibrary.model.Book;

import java.util.List;

public interface BookService {
    List<Book> getBooks();
    Book getById(int id);
    void saveBook(Book book);
}
