package dev.maciejfrackiewicz.task_manager.user.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

public record UserResponse(
        UUID id,
        String email,
        String username,
        LocalDateTime createdAt
) {
}
