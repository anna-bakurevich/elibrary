package com.jd2.elibrary.service.impl.impl;

import com.jd2.elibrary.dao.BookDao;
import com.jd2.elibrary.dao.impl.DefaultBookDao;
import com.jd2.elibrary.model.entity.Book;
import com.jd2.elibrary.service.impl.BookService;

import java.util.List;

public class DefaultBookService implements BookService {
    private BookDao bookDao = DefaultBookDao.getInstance();

    private static BookService instance;
    public static synchronized BookService getInstance() {
        if (instance == null) {
            instance = new DefaultBookService() {
            };
        }
        return instance;
    }

    @Override
    public List<Book> getBooks() {
        return bookDao.getBooks();
    }
}
