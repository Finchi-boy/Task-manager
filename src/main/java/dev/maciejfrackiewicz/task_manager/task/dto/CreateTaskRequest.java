package dev.maciejfrackiewicz.task_manager.task.dto;

import dev.maciejfrackiewicz.task_manager.task.TaskStatus;

import java.time.LocalDateTime;
import java.util.UUID;

public record CreateTaskRequest(
        UUID categoryId,
        String title,
        String description,
        TaskStatus status,
        LocalDateTime deadline
) {

}
