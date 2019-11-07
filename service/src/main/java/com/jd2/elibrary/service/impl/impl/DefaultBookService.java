package com.jd2.elibrary.service.impl.impl;

import com.jd2.elibrary.dao.BookDao;
import com.jd2.elibrary.dao.impl.DefaultBookDao;
import com.jd2.elibrary.model.Book;
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
    public List<Book> getBooks(int pageNumber, int pageSize) {
        return bookDao.getBooks(pageNumber, pageSize);
    }


    @Override
    public Book getById(int id) {
        return bookDao.getById(id);
    }

    @Override
    public void saveBook(Book book) {
        bookDao.saveBook(book);
    }

    @Override
    public void decreaseCountBook(int id, int count) {
        //текущее кол-во
        int oldCount = bookDao.getById(id).getCount();
        //уменьшенное кол-во
        int newCount = oldCount - count;
        //устанавливаем уменьшенное кол-во
        bookDao.updateCountBook(bookDao.getById(id), newCount);
    }

    @Override
    public void increaseCountBook(int id, int count) {
        //текущее кол-во
        int oldCount = bookDao.getById(id).getCount();
        //увеличенное кол-во
        int newCount = oldCount + count;
        //устанавливаем увеличенное кол-во
        bookDao.updateCountBook(bookDao.getById(id), newCount);
    }

    @Override
    public int countPageBooks(int pageSize) {
        List<Book> books = bookDao.getAllBooks();
        return books.size() / pageSize + 1;
    }
}
