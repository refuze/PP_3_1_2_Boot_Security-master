package ru.kata.spring.boot_security.demo.service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.kata.spring.boot_security.demo.dao.UserDao;
import ru.kata.spring.boot_security.demo.exceptions.UserAlreadyExistException;
import ru.kata.spring.boot_security.demo.exceptions.UserNotExistException;
import ru.kata.spring.boot_security.demo.model.User;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class UserServiceImpl implements UserService{

    private final UserDao userDao;
    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Transactional
    @Override
    public void add(User user) {
        if (getByUsername(user.getUsername()) == null) {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            userDao.add(user);
        } else {
            throw new UserAlreadyExistException("User already exist");
        }

    }

    @Override
    public User getById(Long id) {
        return userDao.getById(id);
    }

    @Override
    public User getByUsername(String username) {
        return userDao.getByUsername(username);
    }

    @Override
    public User getByEmail(String email) {
        return userDao.getByEmail(email);
    }

    @Override
    public List<User> getList() {
        return userDao.getList();
    }

    @Transactional
    @Override
    public void update(User user) {
        if (getById(user.getId()) != null) {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            userDao.update(user);
        } else {
            throw new UserNotExistException("User not found");
        }
    }

    @Transactional
    @Override
    public void delete(User user) {
        if (getById(user.getId()) != null) {
            userDao.delete(user);
        } else {
            throw new UserNotExistException("User not found");
        }
    }

}
