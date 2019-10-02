package com.jd2.elibrary.model;

public class User {
    private int id;
    private String first_name;
    private String last_name;
    private String phone;
    private String login;
    private String password;

    public User(String login, String password) {
        this.login = login;
        this.password = password;

    }

    public int getId() {
        return id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public String getPhone() {
        return phone;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

}
