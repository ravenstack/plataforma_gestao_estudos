package com.gestaoestudo.identifyapi.repository;

import com.gestaoestudo.identifyapi.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

// JpaRepository já traz métodos prontos como save(), findAll() e delete()
public interface UserRepository extends JpaRepository<User, Long> {

    // Método customizado para buscar um usuário pelo e-mail (usado no login)
    Optional<User> findByEmail(String email);
}