package com.gestaoestudo.pomodoroapi.repository;

import com.gestaoestudo.pomodoroapi.entity.PomodoroSession;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PomodoroRepository extends JpaRepository<PomodoroSession, Long> {

    List<PomodoroSession> findByUserId(Long userId);

    // Métrica: Total de minutos focados
    @Query("SELECT SUM(s.durationInMinutes) FROM PomodoroSession s WHERE s.userId = :userId AND s.status = 'COMPLETED'")
    Long getTotalFocusMinutes(Long userId);

    // Métrica: Contagem por status (para gráfico de produtividade)
    @Query("SELECT s.status, COUNT(s) FROM PomodoroSession s WHERE s.userId = :userId GROUP BY s.status")
    List<Object[]> getSessionCountByStatus(Long userId);
}