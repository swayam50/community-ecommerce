package com.ecommerce.rest.model.persistent;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;
import com.ecommerce.rest.model.value.UserRole;

@Table(name = "users")
public class User {

    @Id
    @Column("u_id")
    private String id;

    @Column("u_fullname")
    private String name;

    @Column("u_email")
    private String email;

    @Column("u_username")
    private String username;

    @Column("u_password")
    private String password;

    @Column("u_role")
    private UserRole role;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }
}
