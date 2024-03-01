package com.example.UserException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Map;

@RestController
public class UserController {
    @GetMapping("/getUser")
    public ResponseEntity<?> getUser(){
        return ResponseEntity.ok(new User("tato","tato7470"));
    }
    @PostMapping("/postUser")
    public ResponseEntity<?> post(@RequestBody Map<String, String> request) {
        try {
            if (request.size() > 10 || !request.containsKey("login") || !request.containsKey("password")) {
                throw new Exception("Логин или пароль не найдены");
            }
            User user = new User(request.get("login"), request.get("password"));
            return ResponseEntity.ok(user);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }
}
