package com.example.ProductManagementSystemWithOAuth.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column(nullable = false)
    private String firstName;
    @Column(nullable = false)
    private String lastName;
    @Column(nullable = false,unique = true)
    @Email(message = "Invalid email address")
    private String email;
    private String password;
    @ManyToMany(cascade = CascadeType.MERGE,fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_role",
            joinColumns = {
                    @JoinColumn(name = "USERS_ID",referencedColumnName = "ID")
            },
            inverseJoinColumns = {
                    @JoinColumn(name = "ROLES_ID",referencedColumnName = "ID")
            }
    )
    private List<Role> roles;

    public User(User user) {
        this.id = user.getId();
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.email = user.getEmail();
        this.password = user.getPassword();
        this.roles = user.getRoles();
    }

    public User() {
    }
}
