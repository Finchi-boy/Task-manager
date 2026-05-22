package dev.maciejfrackiewicz.task_manager.task;

import dev.maciejfrackiewicz.task_manager.task.Task;
import dev.maciejfrackiewicz.task_manager.task.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TaskService {
    private final TaskRepository taskRepository;

    public Task addTask(Task task)
    {
        return taskRepository.save(task);
    }

    public Optional<Task> getTask(UUID id)
    {
        return taskRepository.findById(id);
    }

    public Task updateTask(Task task)
    {
        return taskRepository.save(task);
    }

    public void deleteTask(UUID id)
    {
        taskRepository.deleteById(id);
    }

}
