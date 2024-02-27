package com.example.UserException;

import java.time.LocalDate;
import java.util.Date;
public class User {
    public String login;
    public String password;
    public LocalDate date;

    public User(String login, String password) {
        this.login = login;
        this.password = password;
        this.date = LocalDate.now();
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
        this.date = LocalDate.now();
    }
}
