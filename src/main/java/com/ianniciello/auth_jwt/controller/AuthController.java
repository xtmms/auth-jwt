package com.ianniciello.auth_jwt.controller;

import com.ianniciello.auth_jwt.dto.AuthRequest;
import com.ianniciello.auth_jwt.dto.JwtResponse;
import com.ianniciello.auth_jwt.model.User;
import com.ianniciello.auth_jwt.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
public class AuthController {

    private final AuthService service;

    public AuthController(AuthService service) {
        this.service = service;
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@Valid @RequestBody AuthRequest dto) {
        return service.register(dto);
    }

    @PostMapping("/login")
    public JwtResponse login(@Valid @RequestBody AuthRequest dto) {
        return service.login(dto);
    }

    @GetMapping("/me")
    public String me(@AuthenticationPrincipal UserDetails user) {
        return "Utente autenticato: " + user.getUsername();
    }
}

