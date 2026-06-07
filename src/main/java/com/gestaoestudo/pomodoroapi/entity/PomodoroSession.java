package com.gestaoestudo.pomodoroapi.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Entity
@Table(name = "pomodoro_sessions")
@Data // Lombok
public class PomodoroSession {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;
    private Long taskId; // Referência ao ID da matéria/tarefa do outro microsserviço
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private Integer durationInMinutes; // Ex: 25

    @Enumerated(EnumType.STRING)
    private SessionStatus status;
}