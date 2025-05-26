package dev.elcio.api.controller;

import java.util.List;
import java.util.ArrayList;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

class User {
    private String name;
    private int age;

    public User() {}

    public User(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}

@RestController
public class UserController {

    private List<User> users = new ArrayList<>(List.of(
        new User("Élcio", 23),
        new User("João", 30),
        new User("Maria", 35)
    ));

    @GetMapping("/user/{id}")
    public User getById(@PathVariable int id) {
        if (id >= 0 && id < users.size()) {
            return users.get(id);
        }
        return null;
    }

    @GetMapping("/user")
    public List<User> getUser(@RequestParam(required = false) String name) {
        if (name == null) {
            return users;
        }

        return users.stream()
            .filter(u -> u.getName().equalsIgnoreCase(name))
            .toList();
    }

    @PostMapping("/user")
    public User createUser(@RequestBody User user) {
        users.add(user);
        return user;
    }
}