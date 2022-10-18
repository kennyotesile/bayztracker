package com.bayztracker.api.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;

@Entity()
public class AppUser {

//    public enum UserType {
//        USER,
//        ADMIN
//    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String password;

    @JsonIgnore
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "roles_id"))
    private Collection<Roles> roles = new ArrayList<>();

    public AppUser() {
    }

    public AppUser(String username, String password, Collection<Roles> roles) {
        this.username = username;
        this.password = password;
        this.roles = roles;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public Collection<Roles> getRole() {
        return roles;
    }

    public void setRole(Collection<Roles> roles) {
        this.roles = roles;
    }
}