package net.mnp.spring.dao.impl;

import net.mnp.spring.dao.UserRepository;
import net.mnp.spring.model.Role;
import net.mnp.spring.model.User;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Dmitry Natalenko on 27.10.2015.
 */
@Repository("userRepository")
@Transactional
public class UserRepositoryImpl implements UserRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public User createUser(User user) {
        entityManager.persist(user);
        return user;
    }


    @Override
    public User updateUser(User user) {
        entityManager.merge(user);
        return user;
    }

    @Override
    public void deleteUser(Long id) {
        User user = entityManager.find(User.class, id);
        entityManager.remove(user);
    }

    @Override
    public User getUserById(Long id) {
        User user = entityManager.find(User.class, id);
        return user;
    }

    @Override
    public User getUserByLogin(String  login) {
        Query query = entityManager.createQuery("SELECT u from User u WHERE u.login= :login");
        query.setParameter("login",login);
        List<User> users = query.getResultList();
        User user = users.get(0);
        return user;
    }

    @Override
    public Set<Role> getRolesByUserId(Long id) {
        Query query = entityManager.createQuery("SELECT ur from Role ur WHERE ur.user.id= :id");
        query.setParameter("id",id);
        List<Role> roles =  query.getResultList();
        return new HashSet<>(roles);
    }
}
