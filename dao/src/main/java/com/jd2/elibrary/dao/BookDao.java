package com.jd2.elibrary.dao;

import com.jd2.elibrary.model.Book;

import java.sql.SQLException;
import java.util.List;

public interface BookDao {

    //create
    void saveBook(Book book) throws SQLException, IllegalAccessException, ClassNotFoundException, InstantiationException;

    //read
    List<Book> getBooks() throws SQLException, IllegalAccessException, ClassNotFoundException, InstantiationException;

    Book getById(int id) throws SQLException, IllegalAccessException, ClassNotFoundException, InstantiationException;

    //update
    void updateBook(Book book) throws SQLException, IllegalAccessException, ClassNotFoundException, InstantiationException;

    //delete
    void removeBook(Book book) throws SQLException, IllegalAccessException, ClassNotFoundException, InstantiationException;

}
