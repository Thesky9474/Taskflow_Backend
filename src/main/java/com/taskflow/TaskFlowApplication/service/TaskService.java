package com.taskflow.TaskFlowApplication.service;

import com.taskflow.TaskFlowApplication.dto.request.CreateTaskRequest;
import com.taskflow.TaskFlowApplication.dto.request.UpdateTaskStatusRequest;
import com.taskflow.TaskFlowApplication.dto.response.TaskResponse;
import org.springframework.data.domain.Page;

public interface TaskService {
    TaskResponse createTask(CreateTaskRequest request);
    Page<TaskResponse> getTasksByUser(Long userId, int page, int size);
    TaskResponse updateTaskStatus(Long taskId, UpdateTaskStatusRequest request);
    void deleteTask(Long taskId);
}
