package dev.maciejfrackiewicz.task_manager.category.dto;

import java.util.UUID;

public record CategoryResponse(
        UUID id,
        String name,
        String color
) {

}
