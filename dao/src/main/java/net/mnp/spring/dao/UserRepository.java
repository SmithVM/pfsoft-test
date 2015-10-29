package net.mnp.spring.dao;

import net.mnp.spring.model.Role;
import net.mnp.spring.model.User;

import java.util.Set;

/**
 * Created by Dmitry Natalenko on 27.10.2015.
 */
public interface UserRepository {

    User createUser(User user);

    User updateUser(User user);

    void deleteUser(Long id);

    User getUserById(Long id);

    User getUserByLogin(String  login);

    Set<Role> getRolesByUserId(Long id);
}
