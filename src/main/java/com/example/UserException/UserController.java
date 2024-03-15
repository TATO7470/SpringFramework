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
        User user = dbWorker.selectUser("tato");
        if (user == null) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Пользователь не найден");
        }
        return ResponseEntity.ok(user);
    }

    @PostMapping("/postUser")
    public ResponseEntity<?> post(@RequestBody Map<String, String> request) {
        try {
            if (request.size() > 4 || !request.containsKey("login") || !request.containsKey("password") || !request.containsKey("email")) {
                throw new Exception("Логин, пароль, почта не найдена ");
            }
            User user = new User(request.get("login"), request.get("password"),request.get("email"));
            int insertRow = dbWorker.insertUser(user);
            if (insertRow > 0) {
                return ResponseEntity.ok(user);
            } else {
                throw new Exception("Такой пользователь уже существует");
            }
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }
}



