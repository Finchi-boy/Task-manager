package dev.maciejfrackiewicz.task_manager.task;


import dev.maciejfrackiewicz.task_manager.task.dto.CreateTaskRequest;
import dev.maciejfrackiewicz.task_manager.task.dto.TaskResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.UUID;

@RestController
@RequestMapping("api/tasks")
@RequiredArgsConstructor
public class TaskController {
    private final TaskService taskService;

    @GetMapping("/{id}")
    public ResponseEntity<TaskResponse> getTask(@PathVariable UUID id) {
        return taskService.getTaskById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<TaskResponse> createTask(@Valid @RequestBody CreateTaskRequest request) {
        TaskResponse newTask = taskService.addTask(request);
        return ResponseEntity.created(URI.create("api/tasks/" + newTask.id())).body(newTask);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TaskResponse> updateTask(@PathVariable UUID id, @Valid @RequestBody CreateTaskRequest request) {
        TaskResponse updatedTask = taskService.updateTask(request);
        return ResponseEntity.ok().body(updatedTask);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<TaskResponse> deleteTask(@PathVariable UUID id) {
        taskService.deleteTask(id);
        return ResponseEntity.noContent().build();
    }


}
