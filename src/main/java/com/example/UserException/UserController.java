package com.example.UserException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Map;

@RestController
public class UserController {

    private DBWorker dbWorker;

    public UserController(DBWorker dbWorker) {
        this.dbWorker = dbWorker;
    }

    @GetMapping("/getUser")
    public ResponseEntity<?> getUser() {
        try {
            User user = dbWorker.selectUser("stas");
            if (user == null) {
                throw new RuntimeException("Пользователь не найден");
            }
            return ResponseEntity.ok(user);
        } catch (RuntimeException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), e);
        }
    }
    @PostMapping("/postUser")
    public ResponseEntity<?> post(@RequestBody Map<String, String> request) {
        try {
            if (request.size() > 10 || !request.containsKey("login") || !request.containsKey("password") || !request.containsKey("data") || !request.containsKey("email")) {
                throw new RuntimeException("Логин или пароль не найдены");
            }
            User user = new User("artemka", "4335345", "2004-01-01", "hfthft");
            int rowsAffected = dbWorker.insertUser(user);

            if (rowsAffected > 0) {
                return ResponseEntity.ok(user);
            } else {
                throw new RuntimeException("Не удалось добавить пользователя в базу данных или пользователь уже с таким логином существует");
            }
        } catch (RuntimeException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage(), e);
        }
    }
}
