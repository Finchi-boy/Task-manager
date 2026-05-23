package dev.maciejfrackiewicz.task_manager.task.dto;

import dev.maciejfrackiewicz.task_manager.task.TaskStatus;

import java.time.LocalDateTime;
import java.util.UUID;

public record TaskResponse (
        UUID id,
        String title,
        String description,
        UUID categoryId,
        TaskStatus status,
        LocalDateTime deadline,
        LocalDateTime createdAt

){
}
