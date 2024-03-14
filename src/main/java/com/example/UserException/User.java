package com.example.UserException;

public class User {
    public String login;
    public String password;
    public String date;
    public String email;

    public User(String login, String password, String date, String email) {
        this.login = login;
        this.password = password;
        this.date = date;
        this.email = email;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
