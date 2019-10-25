package com.jd2.elibrary.dao;

import com.jd2.elibrary.model.Book;

import java.sql.SQLException;
import java.util.List;

public interface BookDao {

    //create
    void saveBook(Book book) throws SQLException;

    //read
    List<Book> getBooks();

    Book getById(int id) throws SQLException;

    //update
    void updateBook(Book book) throws SQLException;

    //delete
    void removeBook(Book book) throws SQLException;

}
