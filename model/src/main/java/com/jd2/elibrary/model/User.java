package com.jd2.elibrary.model;

public class User {
    private int id;
    private String firstName;
    private String lastName;
    private String phone;
    private String login;
    private String password;
    private Role role;


    public User() {
    }

    public User(String firstName, String lastName, String phone, String login, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.login = login;
        this.password = password;
    }

    public User(int id, String firstName, String lastName, String phone, String login, String password, Role role) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.login = login;
        this.password = password;
        this.role = role;
    }

    public User(String firstName, String lastName, String phone, String login, String password, Role role) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.login = login;
        this.password = password;
        this.role = role;
    }

    public int getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
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

    public void setId(int id) {
        this.id = id;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
