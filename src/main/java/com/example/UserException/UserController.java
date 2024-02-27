package com.example.UserException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {
    @GetMapping("/getUser")
    public ResponseEntity<?> getUser(){
        return ResponseEntity.ok(new User("tato","tato7470"));
    }
//    @PostMapping("/postUser")
//    public ResponseEntity<?> postUser(@RequestBody User user){
//        return ResponseEntity.ok(user);
//    }

    @PostMapping("/postUser")
    public ResponseEntity<?> postUser(@RequestBody User user) {
        if (user.getLogin() == null || user.getPassword() == null) {
            throw new UserException("Логин и пароль не найдены");
        }
        return ResponseEntity.ok(user);
    }
    @ExceptionHandler( UserException.class)
    public ResponseEntity<?> postException(UserException ue) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ue.getMessage());
    }
}
