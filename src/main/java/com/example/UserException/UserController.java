package com.example.UserException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import java.util.Map;

@RestController
public class UserController {
    @Autowired
    private WorkWithFiles workWithFiles;
    @Autowired
    private DBWorker dbWorker;

    @GetMapping("/getUser")
    public ResponseEntity<?> getUser(@RequestParam(value = "login") String login) {
        User user = dbWorker.selectUser(login);
        if (user == null) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Пользователь не найден");
        }
        return ResponseEntity.ok(user);
    }

    @PostMapping("/postUser")
    public ResponseEntity<?> postUser(@RequestBody Map<String, String> request) {
        try {
            if (request.size() > 4 || !request.containsKey("login") || !request.containsKey("password") || !request.containsKey("email")) {
                throw new Exception("Логин, пароль, почта не найдены ");
            }
            User user = new User(request.get("login"), request.get("password"), request.get("email"));
            int insertRow = dbWorker.insertUser(user);
            if (insertRow > 0) {
                return ResponseEntity.ok("Количество добавленных пользователей " + insertRow);
            } else {
                throw new Exception("Такой пользователь уже существует");
            }
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }
    @GetMapping("/writeUsers")
    public ResponseEntity<?> writeUsers(@RequestParam(value = "login") String login) {
        User user = DBWorker.selectUser(login);
        if (user == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Пользователь с указанным логином не найден");
        } else {
            workWithFiles.writeFile(String.valueOf(user));
            return ResponseEntity.ok("Информация о пользователе записана в файл.");
        }
    }
}
