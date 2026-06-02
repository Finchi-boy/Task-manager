package dev.maciejfrackiewicz.task_manager.task;

import dev.maciejfrackiewicz.task_manager.category.Category;
import dev.maciejfrackiewicz.task_manager.category.CategoryMapper;
import dev.maciejfrackiewicz.task_manager.category.CategoryRepository;
import dev.maciejfrackiewicz.task_manager.category.dto.CategoryResponse;
import dev.maciejfrackiewicz.task_manager.task.dto.CreateTaskRequest;
import dev.maciejfrackiewicz.task_manager.task.dto.TaskResponse;
import dev.maciejfrackiewicz.task_manager.user.User;
import dev.maciejfrackiewicz.task_manager.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TaskService {
    private final TaskRepository taskRepository;
    private final CategoryRepository categoryRepository;
    private final UserRepository userRepository;
    private final CategoryMapper categoryMapper;

    public TaskResponse addTask(CreateTaskRequest task) {
        return toResponse(taskRepository.save(toEntity(task)));
    }

    public Optional<TaskResponse> getTaskById(UUID id) {
        return taskRepository.findById(id).map(this::toResponse);
    }

    public TaskResponse updateTask(CreateTaskRequest request) {
        return toResponse(taskRepository.save(toEntity(request)));
    }

    public List<TaskResponse> getAllTasksByUserId(UUID id)
    {
        return taskRepository.findByUserId(id)
                .stream()
                .map(this::toResponse)
                .toList();

    }

    public void deleteTask(UUID id) {
        taskRepository.deleteById(id);
    }

    private TaskResponse toResponse(Task task) {
        CategoryResponse categoryResponse = task.getCategory() != null
                ? categoryMapper.toResponse(task.getCategory())
                : null;
        return new TaskResponse(task.getId(), task.getTitle(), task.getDescription(), categoryResponse, task.getStatus(), task.getDeadline(), task.getCreatedAt());
    }

    private Task toEntity(CreateTaskRequest request) {
        UUID userId = (UUID) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        Category category = request.categoryId() != null
                ? categoryRepository.findById(request.categoryId())
                .orElseThrow(() -> new RuntimeException("Category not found"))
                : null;

        return Task.builder()
                .user(user)
                .category(category)
                .title(request.title())
                .description(request.description())
                .status(request.status())
                .deadline(request.deadline())
                .build();
    }

}
