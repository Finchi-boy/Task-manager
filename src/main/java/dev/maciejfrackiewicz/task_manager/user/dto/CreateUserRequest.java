package dev.maciejfrackiewicz.task_manager.user.dto;

public record CreateUserRequest (
    String email,
    String username,
    String password
){}
