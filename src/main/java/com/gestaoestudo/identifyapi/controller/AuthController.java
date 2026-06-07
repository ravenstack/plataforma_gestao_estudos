package com.gestaoestudo.identifyapi.controller;

import java.util.List;
import com.gestaoestudo.identifyapi.entity.User;
import com.gestaoestudo.identifyapi.service.AuthService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public String register(@RequestBody User user) {
        authService.register(user.getEmail(), user.getPassword());
        return "Usuário registrado com sucesso!";
    }
    @GetMapping("/all")
    public List<User> getAllUsers() {
        return authService.findAll(); // Precisamos criar este método no Service
    }
}