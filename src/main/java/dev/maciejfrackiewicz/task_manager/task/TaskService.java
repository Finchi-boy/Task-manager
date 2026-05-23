package dev.maciejfrackiewicz.task_manager.task;
import dev.maciejfrackiewicz.task_manager.task.dto.CreateTaskRequest;
import dev.maciejfrackiewicz.task_manager.task.dto.TaskResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TaskService {
    private final TaskRepository taskRepository;

    public TaskResponse addTask(CreateTaskRequest task)
    {
        return toResponse(taskRepository.save(toEntity(task)));
    }

    public Optional<TaskResponse> getTaskById(UUID id)
    {
        return taskRepository.findById(id).map(this::toResponse);
    }

    public TaskResponse updateTask(CreateTaskRequest request)
    {
        return toResponse(taskRepository.save(toEntity(request)));
    }

    public void deleteTask(UUID id)
    {
        taskRepository.deleteById(id);
    }

    private TaskResponse toResponse(Task task)
    {
        return new TaskResponse(task.getId(), task.getTitle(), task.getDescription(), task.getCategoryId(), task.getStatus(), task.getDeadline(), task.getCreatedAt());
    }

    private Task toEntity(CreateTaskRequest request)
    {
        return Task.builder().categoryId(request.categoryId()).title(request.title()).description(request.description()).status(request.status()).deadline(request.deadline()).build();
    }

}
