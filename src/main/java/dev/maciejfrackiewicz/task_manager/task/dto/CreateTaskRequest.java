package dev.maciejfrackiewicz.task_manager.task.dto;

import dev.maciejfrackiewicz.task_manager.task.TaskStatus;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;
import java.util.UUID;

public record CreateTaskRequest(
        UUID categoryId,
        @NotBlank
        @Size(max=25)
        String title,
        @Size(max=300)
        String description,
        TaskStatus status,
        @Future
        LocalDateTime deadline
) {

}
