package com.gestaoestudo.common;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class HomeController {

    @GetMapping("/")
    public ResponseEntity<Map<String, Object>> home() {
        Map<String, Object> response = new HashMap<>();

        response.put("projeto", "PAES Platform - Gestão de Estudos");
        response.put("status", "Online e Operacional");
        response.put("mensagem", "Monolito de APIs integrado com sucesso via CI/CD!");
        response.put("ambiente", "Produção (Render Cloud)");
        response.put("timestamp", LocalDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME));

        List<Map<String, String>> endpoints = Arrays.asList(
            createEndpoint("GET", "/auth/all", "Listar Utilizadores"),
            createEndpoint("POST", "/auth/register", "Registrar Novo Utilizador"),
            createEndpoint("GET", "/tarefas", "Buscar Tarefas (use ?usuarioId=1)"),
            createEndpoint("GET", "/academic/subjects", "Listar Disciplinas"),
            createEndpoint("GET", "/pomodoro/analytics/1", "Métricas do Pomodoro (use o ID do utilizador)"),
            createEndpoint("GET", "/h2-console", "Painel do Banco de Dados H2")
        );

        response.put("endpoints_disponiveis", endpoints);

        Map<String, String> info = new HashMap<>();
        info.put("versao", "1.0.0");
        info.put("java_version", "21");
        info.put("spring_boot_version", "4.0.5");
        info.put("banco_dados", "H2 (Em Memória)");
        response.put("informacoes_tecnicas", info);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    private Map<String, String> createEndpoint(String metodo, String caminho, String descricao) {
        Map<String, String> endpoint = new HashMap<>();
        endpoint.put("metodo", metodo);
        endpoint.put("caminho", caminho);
        endpoint.put("descricao", descricao);
        return endpoint;
    }
}
