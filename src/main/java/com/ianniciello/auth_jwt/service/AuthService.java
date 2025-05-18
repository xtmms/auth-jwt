package com.ianniciello.auth_jwt.service;

import com.ianniciello.auth_jwt.dto.AuthRequest;
import com.ianniciello.auth_jwt.dto.JwtResponse;
import com.ianniciello.auth_jwt.model.User;
import com.ianniciello.auth_jwt.repository.UserRepository;
import com.ianniciello.auth_jwt.security.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    UserRepository repo;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    JwtUtils jwtUtils;

    public ResponseEntity<String> register(AuthRequest dto) {
        if(repo.findByUsername(dto.getUsername()).isPresent()) {
            throw new RuntimeException("Utente con questo Username già presente, si prega di usare un altro Username");
        }
        User user = new User();
        user.setUsername(dto.getUsername());
        user.setPassword(passwordEncoder.encode(dto.getPassword()));
        user.setRole(dto.getRole());
        repo.save(user);
        return ResponseEntity.status(HttpStatus.CREATED).body("Utente registrato con successo");
    }

    public JwtResponse login(AuthRequest request) {
        User user = repo.findByUsername(request.getUsername())
                .orElseThrow(() -> new UsernameNotFoundException("Username non trovato"));
        if(!passwordEncoder.matches(request.getPassword(), user.getPassword())){
            throw new BadCredentialsException("Password Errata");
        }
        String token = jwtUtils.generateToken(user.getUsername(), user.getRole());
        return new JwtResponse(token, user.getUsername(), user.getRole());


    }

}
