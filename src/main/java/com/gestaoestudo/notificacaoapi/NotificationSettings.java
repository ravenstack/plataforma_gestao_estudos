package com.gestaoestudo.notificacaoapi;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity // Indica que esta classe vai virar uma tabela no banco de dados
@Data // Anotação do Lombok que cria automaticamente os Getters, Setters e toString()
public class NotificationSettings {

    @Id // Define que este campo é a Chave Primária (Primary Key) da tabela
    @GeneratedValue(strategy = GenerationType.IDENTITY) // O banco de dados vai gerar o ID automaticamente (1, 2, 3...)
    private Long id;

    // Armazena o ID do usuário (este ID vem do microsserviço de Usuários)
    private Long usuarioId;

    // Variáveis booleanas (true/false) para salvar as escolhas do usuário
    private boolean avisarVinteQuatroHorasAntes;
    private boolean habilitarPush;
    private boolean habilitarEmail;
}