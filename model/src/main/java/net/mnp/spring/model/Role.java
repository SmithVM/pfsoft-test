package net.mnp.spring.model;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Dmitry Natalenko on 27.10.2015.
 */
@Entity
@Table(name = "USER_ROLES")
public class Role implements Serializable {

    private Long id;
    private User user;
    private String role;

    public Role() {
    }

    public Role(String role){
        this.role = role;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @ManyToOne
    @JoinColumn(name = "USER_ID")
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Column( nullable = false)
    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
