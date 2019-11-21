package com.jd2.elibrary.dao.converter;

import com.jd2.elibrary.dao.entity.BookEntity;
import com.jd2.elibrary.model.Book;
import com.jd2.elibrary.model.BookGenre;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class BookConverterTest {
    @Test
    void toBookNull() {
        final BookEntity bookEntity = BookConverter.convertToBookEntity(null);
        assertNull(bookEntity);
    }

    @Test
    void toBookEntityNull() {
        final Book book = BookConverter.convertToBook(null);
        assertNull(book);
    }

    @Test
    void toListBookNull() {
        final List<Book> bookList = BookConverter.convertToListBook(null);
        assertNull(bookList);
    }

    @Test
    void toBookNotNull() {
        final BookEntity bookEntity = new BookEntity();
        bookEntity.setId(1);
        bookEntity.setGenre(BookGenre.FANTASY);
        bookEntity.setAuthorLastName("Бредбери");
        bookEntity.setCount(20);

        final Book book = BookConverter.convertToBook(bookEntity);
        assertNotNull(book);
        assertEquals(bookEntity.getId(), book.getId());
        assertEquals(bookEntity.getGenre(), book.getGenre());
        assertEquals(bookEntity.getAuthorLastName(), book.getAuthorLastName());
        assertEquals(bookEntity.getCount(), book.getCount());


    }

    @Test
    void toBookEntityNotNull() {
        final Book book = new Book();
        book.setId(1);
        book.setGenre(BookGenre.FANTASY);
        book.setAuthorLastName("Бредбери");
        book.setCount(20);

        final BookEntity bookEntity = BookConverter.convertToBookEntity(book);
        assertNotNull(bookEntity);
        assertEquals(book.getId(), bookEntity.getId());
        assertEquals(book.getGenre(), bookEntity.getGenre());
        assertEquals(book.getAuthorLastName(), bookEntity.getAuthorLastName());
        assertEquals(book.getCount(), bookEntity.getCount());
    }

    @Test
    void toListBookNotNull() {
        final List<BookEntity> bookEntityList = Arrays.asList(new BookEntity(), new BookEntity());

        final List<Book> bookList = BookConverter.convertToListBook(bookEntityList);
        assertNotNull(bookList);
        assertEquals(bookEntityList.size(), bookList.size());
    }
}

