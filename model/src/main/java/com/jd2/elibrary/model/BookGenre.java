package com.jd2.elibrary.model;

public enum BookGenre {
    SCIENTIFIC("Научная литература"),
    PROSE("Проза"),
    POETRY("Поэзия"),
    FANTASY("Фантастика");

    private String name;

    BookGenre(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
