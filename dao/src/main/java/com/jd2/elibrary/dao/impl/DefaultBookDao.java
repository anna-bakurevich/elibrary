package com.jd2.elibrary.dao.impl;

import com.jd2.elibrary.dao.BookDao;
import com.jd2.elibrary.dao.converter.BookConverter;
import com.jd2.elibrary.dao.entity.BookEntity;
import com.jd2.elibrary.dao.repository.BookJpaRepository;
import com.jd2.elibrary.model.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Optional;

public class DefaultBookDao implements BookDao {
    private final BookJpaRepository bookJpaRepository;

    public DefaultBookDao(BookJpaRepository bookJpaRepository) {
        this.bookJpaRepository = bookJpaRepository;
    }

    @Override
    public void save(Book book) {
        BookEntity bookEntity = BookConverter.convertToBookEntity(book);
        bookJpaRepository.save(bookEntity);
    }

    @Override
    public int count() {
        List<BookEntity> books = bookJpaRepository.findAll();
        return books.size();
    }

    @Override
    public List<Book> findAll() {
        List<BookEntity> books = bookJpaRepository.findAll();
        return BookConverter.convertToListBook(books);
    }

   @Override
    public Book findById(int id) {
        Optional<BookEntity> bookEntity = bookJpaRepository.findById(id);
        return BookConverter.convertToBook(bookEntity.get());
    }

    @Override
    public Book findByIsbn(String isbn) {
        BookEntity bookEntity = bookJpaRepository.findByIsbn(isbn);
        return BookConverter.convertToBook(bookEntity);
    }

    @Override
    public void updateCount(int id, int count) {
        bookJpaRepository.updateCount(id, count);
    }

    @Override
    public void deleteById(int id) {
        bookJpaRepository.deleteById(id);
    }

    @Override
    public List<Book> paging(int pageNumber, int size) {
        Page<BookEntity> booksPage = bookJpaRepository.findAll(PageRequest.of(
                pageNumber, size, Sort.Direction.ASC, "title"));
        List<BookEntity> books = booksPage.getContent();
        return BookConverter.convertToListBook(books);
    }
}
