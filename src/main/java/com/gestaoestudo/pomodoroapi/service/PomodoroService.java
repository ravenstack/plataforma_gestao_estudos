package com.gestaoestudo.pomodoroapi.service;

import com.gestaoestudo.pomodoroapi.dto.StudySummaryDTO;
import com.gestaoestudo.pomodoroapi.entity.PomodoroSession;
import com.gestaoestudo.pomodoroapi.entity.SessionStatus;
import com.gestaoestudo.pomodoroapi.repository.PomodoroRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PomodoroService {
    private final PomodoroRepository repository;

    public PomodoroSession startSession(Long userId, Long taskId, Integer duration) {
        PomodoroSession session = new PomodoroSession();
        session.setUserId(userId);
        session.setTaskId(taskId);
        session.setDurationInMinutes(duration);
        session.setStartTime(LocalDateTime.now());
        session.setStatus(SessionStatus.STARTED);
        return repository.save(session);
    }

    public PomodoroSession finishSession(Long id) {
        PomodoroSession session = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Sessão não encontrada"));
        session.setEndTime(LocalDateTime.now());
        session.setStatus(SessionStatus.COMPLETED);
        return repository.save(session);
    }

    public StudySummaryDTO getUserAnalytics(Long userId) {
        Long totalMinutes = repository.getTotalFocusMinutes(userId);
        List<Object[]> counts = repository.getSessionCountByStatus(userId);

        long completed = 0;
        long total = 0;

        for (Object[] row : counts) {
            long count = (long) row[1];
            total += count;
            if (row[0].equals(SessionStatus.COMPLETED)) completed = count;
        }

        double rate = (total > 0) ? (double) completed / total * 100 : 0.0;

        return new StudySummaryDTO(
                totalMinutes != null ? totalMinutes : 0L,
                completed,
                total - completed,
                rate
        );
    }
}
