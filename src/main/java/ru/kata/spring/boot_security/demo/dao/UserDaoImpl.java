package ru.kata.spring.boot_security.demo.dao;

import org.springframework.stereotype.Repository;
import ru.kata.spring.boot_security.demo.model.User;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.stream.Collectors;


@Repository
public class UserDaoImpl implements UserDao {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void add(User user) {
        entityManager.persist(user);
    }

    @Override
    public User getById(Long id) {

        try {
            return (User) entityManager.createQuery("from User user left join fetch user.authorities where user.id = :id").setParameter("id", id).getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public User getByUsername(String username) {

        try {
            return (User) entityManager.createQuery("from User user left join fetch user.authorities where user.username = :username").setParameter("username", username).getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public User getByEmail(String email) {
        try {
            return (User) entityManager.createQuery("from User user left join fetch user.authorities where user.email = :email").setParameter("email", email).getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public List<User> getList() {
        return (List<User>) entityManager.createQuery("from User user left join fetch user.authorities").getResultList().stream().distinct().collect(Collectors.toList());
    }

    @Override
    public void update(User user) {
        entityManager.merge(user);
    }

    @Override
    public void delete(User user) {
        entityManager.remove(entityManager.contains(user) ? user : entityManager.merge(user));
    }
}
