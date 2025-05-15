package com.ianniciello.auth_jwt.dto;

import com.ianniciello.auth_jwt.enums.Role;

public class JwtResponse {

    private String token;
    private String username;
    private Role role;

    public JwtResponse() {
    }

    public JwtResponse(String token, String username, Role role) {
        this.token = token;
        this.username = username;
        this.role = role;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
