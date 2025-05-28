package dev.elcio.api.controller;

import dev.elcio.api.model.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserController {
    private final List<User> users = new ArrayList<>(List.of(
        new User("Élcio", 23),
        new User("João", 30),
        new User("Maria", 35)
    ));

    public List<User> getAll() {
        return users;
    }

    public List<User> findByName(String name) {
        return users.stream()
            .filter(u -> u.getName().equalsIgnoreCase(name))
            .toList();
    }

    public User getById(int id) {
        if (id >= 0 && id < users.size()) {
            return users.get(id);
        }
        return null;
    }

    public User addUser(User user) {
        users.add(user);
        return user;
    }
}