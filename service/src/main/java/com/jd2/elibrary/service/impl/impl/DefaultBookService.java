package com.jd2.elibrary.service.impl.impl;


import com.jd2.elibrary.dao.BookDao;
import com.jd2.elibrary.model.Book;
import com.jd2.elibrary.service.impl.BookService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class DefaultBookService implements BookService {

    public DefaultBookService(BookDao defaultBookDao) {
        this.defaultBookDao = defaultBookDao;
    }

    private final BookDao defaultBookDao;


    @Override
    @Transactional
    public List<Book> paging(int pageNumber, int pageSize) {
        return defaultBookDao.paging(pageNumber, pageSize);
    }


    @Override
    @Transactional
    public Book findById(int id) {
        return defaultBookDao.findById(id);
    }

    @Override
    @Transactional
    public void save(Book book) {
        defaultBookDao.save(book);
    }

    @Override
    @Transactional
    public void decrCountBook(int id, int count) {
        //текущее кол-во
        int oldCount = defaultBookDao.findById(id).getCount();
        //уменьшенное кол-во
        int newCount = oldCount - count;
        //устанавливаем уменьшенное кол-во
        defaultBookDao.updateCount(id, newCount);
    }

    @Override
    @Transactional
    public void incrCountBook(int id, int count) {
        //текущее кол-во
        int oldCount = defaultBookDao.findById(id).getCount();
        //увеличенное кол-во
        int newCount = oldCount + count;
        //устанавливаем увеличенное кол-во
        defaultBookDao.updateCount(id, newCount);
    }

    @Override
    @Transactional
    public int countPageBooks(int pageSize) {
        int count = defaultBookDao.count();
        return count / pageSize + 1;
    }


}
