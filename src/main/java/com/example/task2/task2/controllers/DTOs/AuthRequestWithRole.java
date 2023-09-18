package com.example.task2.task2.controllers.DTOs;

import com.example.task2.task2.data.entities.Role;
import lombok.Data;

import java.util.Set;

@Data
public class AuthRequestWithRole{
    // Other fields like name, username, password, email, etc.
    private Set<Role> roles;

    // Constructors, getters, setters, and other methods

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }
}
