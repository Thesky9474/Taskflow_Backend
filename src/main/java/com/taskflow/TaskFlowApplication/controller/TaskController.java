package com.taskflow.TaskFlowApplication.controller;

import com.taskflow.TaskFlowApplication.dto.request.CreateTaskRequest;
import com.taskflow.TaskFlowApplication.dto.request.UpdateTaskStatusRequest;
import com.taskflow.TaskFlowApplication.dto.response.TaskResponse;
import com.taskflow.TaskFlowApplication.service.TaskService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/tasks")
@RequiredArgsConstructor
public class TaskController {
    private final TaskService taskService;

    @PostMapping
    public ResponseEntity<TaskResponse> createTask(@Valid @RequestBody CreateTaskRequest request){
        return ResponseEntity.ok(taskService.createTask(request));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<Page<TaskResponse>> getUserTasks(
            @PathVariable Long userId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size){
        return ResponseEntity.ok(taskService.getTasksByUser(userId, page, size));
    }

    @PatchMapping("/{taskId}")
    public ResponseEntity<TaskResponse> updateTaskStatus(@PathVariable Long taskId,
                                                         @Valid @RequestBody UpdateTaskStatusRequest request){
        return ResponseEntity.ok(taskService.updateTaskStatus(taskId, request));
    }

    @DeleteMapping("/{taskId}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long taskId){
        taskService.deleteTask(taskId);
        return ResponseEntity.noContent().build();
    }

}
