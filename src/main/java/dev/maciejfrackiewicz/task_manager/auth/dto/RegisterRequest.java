package dev.maciejfrackiewicz.task_manager.auth.dto;

public record RegisterRequest(
        String email,
        String username,
        String password
) {

}
