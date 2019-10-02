package com.jd2.elibrary.model;

public class Book {
    private int id;
    private int isbn;
    private String authorFirstName;
    private String authorLastName;
    private String title;
    private String genre;

    public String getGenre() {
        return genre;
    }

    public int getId() {
        return id;
    }

    public int getIsbn() {
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
}
