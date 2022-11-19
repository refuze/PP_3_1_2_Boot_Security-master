package ru.kata.spring.boot_security.demo.service;

import ru.kata.spring.boot_security.demo.model.User;

import java.util.List;

public interface UserService {
    void add(User user);

    User getById(Long id);

    List<User> getByUsername(String username);

    List<User> getList();

    void update(User user);

    void delete(User user);
}
