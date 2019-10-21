package com.jd2.elibrary.dao.impl;

import com.jd2.elibrary.dao.BookDao;
import com.jd2.elibrary.dao.DataSource;
import com.jd2.elibrary.model.entity.Book;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DefaultBookDao implements BookDao {
    private static final Logger log = LoggerFactory.getLogger(DefaultUserDao.class);
    private static DefaultBookDao instance;

    public static synchronized DefaultBookDao getInstance() {
        if (instance == null) {
            instance = new DefaultBookDao();
        }
        return instance;
    }

    private Connection connect() {
        return DataSource.getInstance().getConnection();
    }


    @Override
    public void saveBook(Book book) throws SQLException {
        try (Connection connection = connect();
             PreparedStatement preparedStatement = connection.prepareStatement
                     ("INSERT INTO book(isbn, author_first_name, author_last_name,title, genre, count) " +
                             "VALUES (?, ?, ?, ?, ?, ?)")) {
            preparedStatement.setString(1, book.getIsbn());
            preparedStatement.setString(2, book.getAuthorFirstName());
            preparedStatement.setString(3, book.getAuthorLastName());
            preparedStatement.setString(4, book.getTitle());
            preparedStatement.setString(5, book.getGenre());
            preparedStatement.setInt(6, book.getCount());

            preparedStatement.executeUpdate();
        }
    }

    @Override
    public List<Book> getBooks() {

        try (Connection connection = connect();
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM book");
             ResultSet resultSet = preparedStatement.executeQuery()) {
            final List<Book> bookList = new ArrayList<>();
            while (resultSet.next()) {
                Book book = new Book();
                book.setId(resultSet.getInt("id"));
                book.setIsbn(resultSet.getString("isbn"));
                book.setAuthorFirstName(resultSet.getString("author_first_name"));
                book.setAuthorLastName(resultSet.getString("author_last_name"));
                book.setTitle(resultSet.getString("title"));
                book.setGenre(resultSet.getString("genre"));
                book.setCount(resultSet.getInt("count"));

                bookList.add(book);
            }
            return bookList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Book getById(int id) {
        Book book = new Book();
        try (Connection connection = connect();
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM book WHERE id=?")) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            {

                book.setId(resultSet.getInt("id"));
                book.setIsbn(resultSet.getString("isbn"));
                book.setAuthorFirstName(resultSet.getString("author_first_name"));
                book.setAuthorLastName(resultSet.getString("author_last_name"));
                book.setTitle(resultSet.getString("title"));
                book.setGenre(resultSet.getString("genre"));
                book.setCount(resultSet.getInt("count"));
            }
            return book;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void updateBook(Book book) throws SQLException {
        try (Connection connection = connect();
             PreparedStatement preparedStatement = connection.prepareStatement("UPDATE book SET isbn=?, " +
                     "author_first_name=?, author_last_name=?, title=?, genre=?, count=? WHERE id=?")) {

            preparedStatement.setString(1, book.getIsbn());
            preparedStatement.setString(2, book.getAuthorFirstName());
            preparedStatement.setString(3, book.getAuthorLastName());
            preparedStatement.setString(4, book.getTitle());
            preparedStatement.setString(5, book.getGenre());
            preparedStatement.setInt(6, book.getCount());

        }
    }

    @Override
    public void removeBook(Book book) throws SQLException {
        try (Connection connection = connect();
             PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM book WHERE id=?")) {

            preparedStatement.setInt(1, book.getId());
        }

    }
}
