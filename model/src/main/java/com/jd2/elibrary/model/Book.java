package com.jd2.elibrary.model;

public class Book {
    private int id;
    private String isbn;
    private String authorFirstName;
    private String authorLastName;
    private String title;
    private BookGenre genre;
    private int count;

    public Book() {
    }

    public Book(int id, String isbn, String authorFirstName, String authorLastName, String title, BookGenre genre, int count) {
        this.id = id;
        this.isbn = isbn;
        this.authorFirstName = authorFirstName;
        this.authorLastName = authorLastName;
        this.title = title;
        this.genre = genre;
        this.count = count;
    }

    public int getCount() {
        return count;
    }

    public BookGenre getGenre() {
        return genre;
    }

    public int getId() {
        return id;
    }

    public String getIsbn() {
        return isbn;
    }

    public String getAuthorFirstName() {
        return authorFirstName;
    }

    public String getAuthorLastName() {
        return authorLastName;
    }

    public String getTitle() {
        return title;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public void setAuthorFirstName(String authorFirstName) {
        this.authorFirstName = authorFirstName;
    }

    public void setAuthorLastName(String authorLastName) {
        this.authorLastName = authorLastName;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setGenre(BookGenre genre) {
        this.genre = genre;
    }

    public void setCount(int count) {
        this.count = count;
    }

}
