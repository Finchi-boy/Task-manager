package dev.maciejfrackiewicz.task_manager.task.dto;

import dev.maciejfrackiewicz.task_manager.category.Category;
import dev.maciejfrackiewicz.task_manager.category.dto.CategoryResponse;
import dev.maciejfrackiewicz.task_manager.task.TaskStatus;

import java.time.LocalDateTime;
import java.util.UUID;

public record TaskResponse (
        UUID id,
        String title,
        String description,
        CategoryResponse categoryReposne,
        TaskStatus status,
        LocalDateTime deadline,
        LocalDateTime createdAt

){
}
