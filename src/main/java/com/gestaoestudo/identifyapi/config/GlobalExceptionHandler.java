package com.gestaoestudo.identifyapi.config;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice // Esta anotação diz ao Spring: "vigie todos os controllers"
public class GlobalExceptionHandler {

    // Sempre que um erro do tipo RuntimeException (o que você usou) acontecer...
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<String> handleRuntimeException(RuntimeException e) {

        // ...nós devolvemos o código 400 (Bad Request) e a mensagem do erro no corpo
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body("Erro na validação: " + e.getMessage());
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<String> handleDataIntegrity(DataIntegrityViolationException e) {
        // Se cair aqui, é porque o banco barrou algo (como o e-mail duplicado)
        return ResponseEntity
                .status(HttpStatus.CONFLICT) // Código 409: Conflito
                .body("Erro: Já existe um registro com estes dados (e-mail duplicado).");
    }
}