package com.gestaoestudo.notificacaoapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication //Java que esta é uma aplicação Spring Boot e ativa configurações automáticas
public class PrjPaesApplication {

    // Método principal que serve como ponto de partida (entry point) do programa
    public static void main(String[] args) {
        // Inicializa o servidor e roda a aplicação na porta configurada (8084)
        SpringApplication.run(PrjPaesApplication.class, args);
    }
}