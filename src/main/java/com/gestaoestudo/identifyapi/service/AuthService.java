package com.gestaoestudo.identifyapi.service;

import com.gestaoestudo.identifyapi.entity.User;
import com.gestaoestudo.identifyapi.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User register(String email, String password) {
        if (!isEmailValido(email)) throw new RuntimeException("E-mail inválido");
        if (!isSenhaForte(password)) throw new RuntimeException("Senha fraca");


        User user = new User();
        user.setEmail(email);
        // Criptografia conforme RNF-02
        user.setPassword(passwordEncoder.encode(password));
        return userRepository.save(user);
    }

    // Lógica trazida do Usuario.java original
    private boolean isEmailValido(String email) {
        return email != null && email.matches("^[\\w!#$%&'*+/=?`{|}~^.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$");
    }

    private boolean isSenhaForte(String senha) {
        if (senha == null || senha.length() < 8) return false;
        boolean temMaiuscula = senha.chars().anyMatch(Character::isUpperCase);
        boolean temMinuscula = senha.chars().anyMatch(Character::isLowerCase);
        boolean temNumero = senha.chars().anyMatch(Character::isDigit);
        return temMaiuscula && temMinuscula && temNumero;
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }
}