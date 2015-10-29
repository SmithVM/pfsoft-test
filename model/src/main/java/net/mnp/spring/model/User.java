package net.mnp.spring.model;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Dmitry Natalenko on 27.10.2015.
 */
@Entity
@Table(name = "USERS")
public class User implements Serializable {

    private Long id;
    private String login;
    private String password;
    private boolean enabled;
    private Set<Role> roles =  new HashSet<>(0);

    public User() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column( unique = true, nullable = false, length = 12)
    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }


    @Column( nullable = false)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    @Column( nullable = false)
    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    @OneToMany(targetEntity = Role.class, mappedBy = "user", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    public Set<Role> getRoles() {
        return this.roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 31).
                append(id).
                append(login).
                toHashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof User))
            return false;
        if (obj == this)
            return true;

        User user = (User) obj;
        return new EqualsBuilder().
                        append(id, user.id).
                append(login, user.login).
                isEquals();
    }
}





