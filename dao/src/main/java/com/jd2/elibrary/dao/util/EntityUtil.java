package com.jd2.elibrary.dao.util;

import com.jd2.elibrary.dao.entity.BookEntity;
import com.jd2.elibrary.dao.entity.OrderEntity;
import com.jd2.elibrary.dao.entity.UserEntity;
import com.jd2.elibrary.model.Book;
import com.jd2.elibrary.model.Order;
import com.jd2.elibrary.model.User;

import java.util.ArrayList;
import java.util.List;

public class EntityUtil {
    public static UserEntity convertToUserEntity(User user) {
        UserEntity userEntity = new UserEntity();
        userEntity.setId(user.getId());
        userEntity.setFirstName(user.getFirstName());
        userEntity.setLastName(user.getLastName());
        userEntity.setPhone(user.getPhone());
        userEntity.setLogin(user.getLogin());
        userEntity.setPassword(user.getPassword());
        userEntity.setRole(user.getRole());
        return userEntity;
    }
    public static User convertToUser(UserEntity userEntity) {
        User user = new User();
        user.setId(userEntity.getId());
        user.setFirstName(userEntity.getFirstName());
        user.setLastName(userEntity.getLastName());
        user.setPhone(userEntity.getPhone());
        user.setLogin(userEntity.getLogin());
        user.setPassword(userEntity.getPassword());
        user.setRole(userEntity.getRole());
        return user;
    }


    public static BookEntity convertToBookEntity(Book book) {
        BookEntity bookEntity = new BookEntity();
        bookEntity.setId(book.getId());
        bookEntity.setIsbn(book.getIsbn());
        bookEntity.setAuthorFirstName(book.getAuthorFirstName());
        bookEntity.setAuthorLastName(book.getAuthorLastName());
        bookEntity.setTitle(book.getTitle());
        bookEntity.setGenre(book.getGenre());
        bookEntity.setCount(book.getCount());
        return bookEntity;
    }

    public static Book convertToBook(BookEntity bookEntity) {
        Book book = new Book();
        book.setId(bookEntity.getId());
        book.setIsbn(bookEntity.getIsbn());
        book.setAuthorFirstName(bookEntity.getAuthorFirstName());
        book.setAuthorLastName(bookEntity.getAuthorLastName());
        book.setTitle(bookEntity.getTitle());
        book.setGenre(bookEntity.getGenre());
        book.setCount(bookEntity.getCount());
        return book;
    }

    public static OrderEntity convertToOrderEntity(Order order) {
        OrderEntity orderEntity = new OrderEntity();
        orderEntity.setId(order.getId());
        orderEntity.setUserEntity(convertToUserEntity(order.getUser()));
        orderEntity.setOrderDate(order.getOrderDate());
        orderEntity.setReturnDate(order.getReturnDate());
        orderEntity.setOrderStatus(order.getOrderStatus());
        return orderEntity;
    }

    public static Order convertToOrder(OrderEntity orderEntity) {
        Order order = new Order();
        order.setId(orderEntity.getId());
        order.setUser(convertToUser(orderEntity.getUserEntity()));
        order.setOrderDate(orderEntity.getOrderDate());
        order.setReturnDate(orderEntity.getReturnDate());
        order.setOrderStatus(orderEntity.getOrderStatus());
        return order;
    }
    
    public static List<Book> convertListToBook(List<BookEntity> booksEntity){
        List<Book> books = new ArrayList<>();
        for (BookEntity be:booksEntity) {
            books.add(convertToBook(be));
        }
        return books;
    }

}
