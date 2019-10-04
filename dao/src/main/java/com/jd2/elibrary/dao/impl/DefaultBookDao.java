package com.jd2.elibrary.dao.impl;

import com.jd2.elibrary.dao.BookDao;
import com.jd2.elibrary.model.Book;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class DefaultBookDao implements BookDao {
    private static DefaultBookDao instance;

    public static synchronized DefaultBookDao getInstance(){
        if(instance==null){
            instance = new DefaultBookDao();
        }
        return instance;
    }

    public Connection connect() throws SQLException {
        ResourceBundle resource = ResourceBundle.getBundle("db");
        String url = resource.getString("url");
        String user = resource.getString("user");
        String password = resource.getString("password");
        return DriverManager.getConnection(url, user, password);
    }

    @Override
    public void addBook(Book book) throws SQLException {
        BookDao dataBase = DefaultBookDao.getInstance();
        try (Connection connection = dataBase.connect();
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
    public List<Book> getBooks() throws SQLException {
        List<Book> bookList = new ArrayList<>();
        BookDao dataBase = DefaultBookDao.getInstance();

        try (Connection connection = dataBase.connect();
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
    public Book getById(int id) throws SQLException {
        Book book = new Book();
        BookDao dataBase = DefaultBookDao.getInstance();
        try (Connection connection = dataBase.connect();
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
    public void updateBook(Book book) throws SQLException {
        BookDao dataBase = DefaultBookDao.getInstance();
        try (Connection connection = dataBase.connect();
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
    public void removeBook(Book book) throws SQLException {
        BookDao dataBase = DefaultBookDao.getInstance();
        try (Connection connection = dataBase.connect();
             PreparedStatement preparedStatement = connection.prepareStatement("delete from book where id=?")) {

            preparedStatement.setInt(1, book.getId());
        }

    }
}
