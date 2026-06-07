package com.gestaoestudo.taskapi;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data // Lombok gera os Getters e Setters
public class Tarefa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long usuarioId; // ID do dono da tarefa
    private String titulo; // Ex: "Estudar para prova de Banco de Dados"
    private String dataLimite; // Usamos String para facilitar a criação (ex: "2026-04-10")
    private boolean concluida; // Status da tarefa (true = feita, false = pendente)
}