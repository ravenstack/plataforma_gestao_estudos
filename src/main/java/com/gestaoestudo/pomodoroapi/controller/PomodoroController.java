package com.gestaoestudo.pomodoroapi.controller;

import com.gestaoestudo.pomodoroapi.dto.StudySummaryDTO;
import com.gestaoestudo.pomodoroapi.entity.PomodoroSession;
import com.gestaoestudo.pomodoroapi.service.PomodoroService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pomodoro")
@RequiredArgsConstructor
public class PomodoroController {
    private final PomodoroService service;

    @PostMapping("/start")
    public ResponseEntity<PomodoroSession> start(@RequestParam Long userId, @RequestParam Long taskId) {
        return ResponseEntity.ok(service.startSession(userId, taskId, 25));
    }

    @PatchMapping("/{id}/finish")
    public ResponseEntity<PomodoroSession> finish(@PathVariable Long id) {
        return ResponseEntity.ok(service.finishSession(id));
    }

    @GetMapping("/analytics/{userId}")
    public ResponseEntity<StudySummaryDTO> getAnalytics(@PathVariable Long userId) {
        return ResponseEntity.ok(service.getUserAnalytics(userId));
    }
}