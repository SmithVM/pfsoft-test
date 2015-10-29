package net.mnp.spring.service.security;

import net.mnp.spring.dao.UserRepository;
import net.mnp.spring.model.Role;
import net.mnp.spring.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Created by Dmitry Natalenko on 27.10.2015.
 */
@Component
public class CustomUserDetailService implements UserDetailsService {


    @Autowired
    private UserRepository userRepository;


    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        User user = userRepository.getUserByLogin(login);
        Long id = user.getId();
        Set<Role> roles = userRepository.getRolesByUserId(id);
        List<GrantedAuthority> authorities = getGrantedAuthorities(roles);

        UserDetails userDetails =  new org.springframework.security.core.userdetails.User(user.getLogin(),
                user.getPassword(),
                authorities);
        return userDetails;
    }

    private List<GrantedAuthority> getGrantedAuthorities(Set<Role > roles) {
        List<GrantedAuthority> authorities = new ArrayList<>();
        for (Role role : roles) {
            authorities.add(new SimpleGrantedAuthority(role.getRole()));
        }
        return authorities;
    }
}
