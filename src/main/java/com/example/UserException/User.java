package com.example.UserException;

import java.time.LocalDate;

public class User {
    public String login;
    public String password;
    public LocalDate date;
    public String email;

    public User(String login, String password,String email) {
        this.login = login;
        this.password = password;
        this.date = LocalDate.now();
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

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "User{" +
                "login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", date=" + date +
                ", email='" + email + '\'' +
                '}';
    }
}

