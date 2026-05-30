package dev.maciejfrackiewicz.task_manager.config;

import java.time.LocalDateTime;

public record ApiError(
        int status,
        String message,
        LocalDateTime timestamp
) {}
