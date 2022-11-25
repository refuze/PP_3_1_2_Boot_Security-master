package ru.kata.spring.boot_security.demo.dao;

import ru.kata.spring.boot_security.demo.model.User;

import java.util.List;

public interface UserDao {
    void add(User user);

    User getById(Long id);

    User getByUsername(String username);

    User getByEmail(String email);

    List<User> getList();

    void update(User user);

    void delete(User user);
}
