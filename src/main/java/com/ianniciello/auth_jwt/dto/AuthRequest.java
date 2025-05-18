package com.ianniciello.auth_jwt.dto;

import com.ianniciello.auth_jwt.enums.Role;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public class AuthRequest {

    private Long id;

    @NotBlank
    private String username;

    @Pattern(
            regexp = "^[A-Z][a-zA-Z0-9]{7,}$",
            message = "La password deve iniziare con una maiuscola e contenere solo lettere e numeri (min 8 caratteri)"
    )
    private String password;

    @NotNull
    private Role role;

    public AuthRequest() {}

    public AuthRequest(Long id, String username, Role role) {

        this.id = id;
        this.username = username;
        this.role = role;

    }

    public AuthRequest(String username, String password, Role role) {

        this.username = username;
        this.password = password;
        this.role = role;

    }

    public AuthRequest(String username, String password) {

        this.username = username;
        this.password = password;

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

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
