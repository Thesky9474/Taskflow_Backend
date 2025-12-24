package com.taskflow.TaskFlowApplication.service;

import com.taskflow.TaskFlowApplication.dto.request.CreateTaskRequest;
import com.taskflow.TaskFlowApplication.dto.request.UpdateTaskStatusRequest;
import com.taskflow.TaskFlowApplication.dto.response.TaskResponse;
import com.taskflow.TaskFlowApplication.entity.Task;
import com.taskflow.TaskFlowApplication.entity.User;
import com.taskflow.TaskFlowApplication.exception.ResourceNotFoundException;
import com.taskflow.TaskFlowApplication.repository.TaskRepository;
import com.taskflow.TaskFlowApplication.repository.UserRepository;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService{

    private final NotificationService notificationService;
    private final TaskRepository taskRepository;
    private final UserRepository userRepository;


    @Override
    public TaskResponse createTask(CreateTaskRequest request) {
        User user = userRepository.findById(request.getUserId()).orElseThrow(() -> new RuntimeException("User Not found"));

        Task task = new Task();
        task.setTitle(request.getTitle());
        task.setDescription(request.getDescription());
        task.setStatus(request.getStatus());
        task.setAssignedTo(user);

        Task saved = taskRepository.save(task);

        notificationService.sendTaskNotification(saved.getId());

        return new TaskResponse(
                saved.getId(),
                saved.getTitle(),
                saved.getStatus(),
                user.getId()
        );
    }

    @Override
    @Transactional(readOnly = true)
    public Page<TaskResponse> getTasksByUser(Long userId, int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("id").descending());

        return taskRepository.findTaskWithUser(userId, pageable)
                .map(t -> new TaskResponse(
                        t.getId(),
                        t.getTitle(),
                        t.getStatus(),
                        t.getAssignedTo().getId()
                ));
    }

    @Override
    @org.springframework.transaction.annotation.Transactional(readOnly = true)
    public TaskResponse updateTaskStatus(Long taskId, UpdateTaskStatusRequest request){
        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new ResourceNotFoundException("Task not found"));

        task.setStatus(request.getStatus());

        return new TaskResponse(
                task.getId(),
                task.getTitle(),
                task.getStatus(),
                task.getAssignedTo().getId()
        );
    }

    @Override
    @Transactional
    public void deleteTask(Long taskId){
        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new ResourceNotFoundException("Task not found"));

        taskRepository.delete(task);
    }
}
