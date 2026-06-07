package com.gestaoestudo.pomodoroapi.dto;

public record StudySummaryDTO(
        Long totalMinutes,
        Long completedSessions,
        Long abandonedSessions,
        Double productivityRate
) {}