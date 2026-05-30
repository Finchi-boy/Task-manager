package dev.maciejfrackiewicz.task_manager.category;

import dev.maciejfrackiewicz.task_manager.category.dto.CategoryResponse;
import dev.maciejfrackiewicz.task_manager.category.dto.CreateCategoryRequest;
import dev.maciejfrackiewicz.task_manager.user.User;
import dev.maciejfrackiewicz.task_manager.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class CategoryMapper {
    private final UserRepository userRepository;

    public Category toEntity(CreateCategoryRequest request)
    {
        UUID userId = (UUID) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return Category.builder()
                .user(user)
                .name(request.name())
                .color(request.color())
                .build();
    }

    public CategoryResponse toResponse(Category category)
    {
        return new CategoryResponse(category.getId(), category.getName(), category.getColor());
    }
}
