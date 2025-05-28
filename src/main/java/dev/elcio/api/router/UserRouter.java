package dev.elcio.api.router;

import dev.elcio.api.controller.UserController;
import dev.elcio.api.model.User;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserRouter {

    @Autowired
    private UserController userController;

    @GetMapping("/user/{id}")
    public ResponseEntity<User> getById(@PathVariable int id) {
        User user = userController.getById(id);
        if (user != null) {
            return ResponseEntity.ok(user);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/user")
    public List<User> getUser(@RequestParam(required = false) String name) {
        if (name == null) {
            return userController.getAll();
        }
        return userController.findByName(name);
    }

    @PostMapping("/user")
    public ResponseEntity<User> createUser(@RequestBody @Valid User user) {
        userController.addUser(user);
        return ResponseEntity.status(201).body(user);
    }
}