package dev.maciejfrackiewicz.task_manager.auth.dto;

public record LoginRequest(
        String email,
        String password
) {
}
