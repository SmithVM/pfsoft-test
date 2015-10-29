package net.mnp.spring.service.impl;

import net.mnp.spring.dao.UserRepository;
import net.mnp.spring.model.Role;
import net.mnp.spring.model.User;
import net.mnp.spring.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Dmitry Natalenko on 27.10.2015.
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;

    @Override
    public User createUser(User user) {
        user.setEnabled(true);
        String password = user.getPassword();
        String encryptedPassword = passwordEncoder.encode(password);
        user.setPassword(encryptedPassword);
        Role role = new Role("ROLE_USER");
        role.setUser(user);
        Set<Role> roleList = new HashSet<>();
        roleList.add(role);
        user.setRoles(roleList);
        return userRepository.createUser(user);
    }

    @Override
    public User updateUser(User user) {
        return userRepository.updateUser(user);
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteUser(id);
    }

    @Override
    public User getUserById(Long id) {
        return userRepository.getUserById(id);
    }

    @Override
    public User getUserByLogin(String login) {
        return userRepository.getUserByLogin(login);
    }

    @Override
    public Set<Role> getRolesByUserId(Long id) { return userRepository.getRolesByUserId(id);}
}