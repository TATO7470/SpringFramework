package com.example.UserException;
import org.springframework.stereotype.Repository;

import java.sql.*;

@Repository
public class DBWorker {
        private static  String URL = "jdbc:postgresql://localhost:5432/DBtato";
        private static  String USER = "tato";
        private static  String PASSWORD = "123456";
        public static User selectUser(String login) {
                User user = null;
                try {
                       Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
                        Statement statement = connection.createStatement();
                        String request = "SELECT a.login, a.password, a.date, e.email " +
                                "FROM user_data AS a " +
                                "JOIN user_email e ON a.login = e.login " +
                                "WHERE a.login = '" + login + "'";
                        ResultSet userData = statement.executeQuery(request);
                        if (userData.next()) {
                                String Login = userData.getString("login");
                                String Password = userData.getString("password");
                                //String Data = userData.getString("date");
                                String Email = userData.getString("email");
                                user = new User(Login, Password, Email);
                        }
                } catch (Exception e) {
                        System.err.println("Соединение с базой данных не установлено " + e.getMessage());
                }
            return user;
        }
        public int insertUser (User user) {
                int insertUser = 0;
                String request = "INSERT INTO user_data (login, password, date) VALUES (?, ?, ?); " +
                                 "INSERT INTO user_email (login, email) VALUES (?, ?)";
                try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
                     PreparedStatement preparedStatement = connection.prepareStatement(request)) {
                        preparedStatement.setString(1, user.getLogin());
                        preparedStatement.setString(2, user.getPassword());
                        preparedStatement.setDate(3, Date.valueOf(String.valueOf(user.getDate())));
                        preparedStatement.setString(4, user.getLogin());
                        preparedStatement.setString(5, user.getEmail());
                        return preparedStatement.executeUpdate();
                } catch (Exception e) {
                        System.err.println("NO DATABASE " + e.getMessage());
                }
            return 0;
        }

}

