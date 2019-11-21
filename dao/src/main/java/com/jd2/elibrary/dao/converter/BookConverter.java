package com.jd2.elibrary.dao.converter;

import com.jd2.elibrary.dao.entity.BookEntity;
import com.jd2.elibrary.model.Book;

import java.util.ArrayList;
import java.util.List;

public class BookConverter {
    public static BookEntity convertToBookEntity(Book book) {
        if (book == null) {
            return null;
        }
        final BookEntity bookEntity = new BookEntity();
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
        if (bookEntity == null) {
            return null;
        }
        final Book book = new Book();
        book.setId(bookEntity.getId());
        book.setIsbn(bookEntity.getIsbn());
        book.setAuthorFirstName(bookEntity.getAuthorFirstName());
        book.setAuthorLastName(bookEntity.getAuthorLastName());
        book.setTitle(bookEntity.getTitle());
        book.setGenre(bookEntity.getGenre());
        book.setCount(bookEntity.getCount());
        return book;
    }


    public static List<Book> convertToListBook(List<BookEntity> booksEntity) {
        if (booksEntity == null){
            return null;
        }
        final List<Book> books = new ArrayList<>();
        for (BookEntity be : booksEntity) {
            books.add(convertToBook(be));
        }
        return books;
    }
}
