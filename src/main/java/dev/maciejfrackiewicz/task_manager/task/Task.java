package dev.maciejfrackiewicz.task_manager.task;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name="tasks")

@Getter
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private UUID userId;
    private UUID categoryId;
    private String title;
    private String description;
    @Enumerated(EnumType.STRING)
    private TaskStatus status;
    private LocalDateTime deadline;
    private LocalDateTime createdAt;

    @PrePersist
    protected void onCreate(){
        this.createdAt = LocalDateTime.now();
    }

}
