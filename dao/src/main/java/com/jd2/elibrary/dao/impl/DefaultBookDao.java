package com.jd2.elibrary.dao.impl;

import com.jd2.elibrary.dao.BookDao;
import com.jd2.elibrary.dao.DataSource;
import com.jd2.elibrary.model.Book;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DefaultBookDao implements BookDao {
    private static final Logger log = LoggerFactory.getLogger(DefaultUserDao.class);
    private static DefaultBookDao instance;

    public static synchronized DefaultBookDao getInstance(){
        if(instance==null){
            instance = new DefaultBookDao();
        }
        return instance;
    }

    private Connection connect() throws IllegalAccessException, InstantiationException, ClassNotFoundException {
        return DataSource.getInstance().getConnection();
    }


    @Override
    public void saveBook(Book book) throws SQLException, IllegalAccessException, ClassNotFoundException,
            InstantiationException {
        try (Connection connection = connect();
             PreparedStatement preparedStatement = connection.prepareStatement
                     ("insert into book(isbn, author_first_name, author_last_name,title, genre) values (?, ?, ?, ?, ?)")) {
            preparedStatement.setString(1, book.getIsbn());
            preparedStatement.setString(2, book.getAuthorFirstName());
            preparedStatement.setString(3, book.getAuthorLastName());
            preparedStatement.setString(4, book.getTitle());
            preparedStatement.setString(5, book.getGenre());

            preparedStatement.executeUpdate();
        }
    }

    @Override
    public List<Book> getBooks() throws SQLException, IllegalAccessException, ClassNotFoundException,
            InstantiationException {
        List<Book> bookList = new ArrayList<>();
        try (Connection connection = connect();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("select * from book")) {
            while (resultSet.next()) {
                Book book = new Book();
                book.setId(resultSet.getInt("id"));
                book.setIsbn(resultSet.getString("isbn"));
                book.setAuthorFirstName(resultSet.getString("author_first_name"));
                book.setAuthorLastName(resultSet.getString("author_last_name"));
                book.setTitle(resultSet.getString("title"));
                book.setGenre(resultSet.getString("genre"));

                bookList.add(book);
            }
        }
        return bookList;
    }

    @Override
    public Book getById(int id) throws SQLException, IllegalAccessException, ClassNotFoundException,
            InstantiationException {
        Book book = new Book();
        try (Connection connection = connect();
             PreparedStatement preparedStatement = connection.prepareStatement("select * from book where id=?")) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            book.setId(resultSet.getInt("id"));
            book.setIsbn(resultSet.getString("isbn"));
            book.setAuthorFirstName(resultSet.getString("author_first_name"));
            book.setAuthorLastName(resultSet.getString("author_last_name"));
            book.setTitle(resultSet.getString("title"));
            book.setGenre(resultSet.getString("genre"));
        }
        return book;
    }

    @Override
    public void updateBook(Book book) throws SQLException, IllegalAccessException, ClassNotFoundException,
            InstantiationException {
        try (Connection connection = connect();
             PreparedStatement preparedStatement = connection.prepareStatement("update book set isbn=?, " +
                     "author_first_name=?, author_last_name=?, title=?, genre=? where id=?")) {

            preparedStatement.setString(1, book.getIsbn());
            preparedStatement.setString(2, book.getAuthorFirstName());
            preparedStatement.setString(3, book.getAuthorLastName());
            preparedStatement.setString(4, book.getTitle());
            preparedStatement.setString(5, book.getGenre());

        }
    }

    @Override
    public void removeBook(Book book) throws SQLException, IllegalAccessException, ClassNotFoundException,
            InstantiationException {
        try (Connection connection = connect();
             PreparedStatement preparedStatement = connection.prepareStatement("delete from book where id=?")) {

            preparedStatement.setInt(1, book.getId());
        }

    }
}
