package com.taskflow.TaskFlowApplication.service;

import com.taskflow.TaskFlowApplication.dto.request.CreateTaskRequest;
import com.taskflow.TaskFlowApplication.dto.response.TaskResponse;
import com.taskflow.TaskFlowApplication.entity.Task;
import com.taskflow.TaskFlowApplication.entity.User;
import com.taskflow.TaskFlowApplication.repository.TaskRepository;
import com.taskflow.TaskFlowApplication.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TaskServiceTest {

    @Mock
    private TaskRepository taskRepository;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private TaskServiceImpl taskService;

    @Test
    void shouldCreateTaskSuccessfully(){
        User user = new User();
        user.setId(1L);

        CreateTaskRequest request = new CreateTaskRequest();
        request.setTitle("Test Task");
        request.setStatus("OPEN");
        request.setUserId(1L);

        when(userRepository.findById(1L))
                .thenReturn(Optional.of(user));

        when(taskRepository.save(any(Task.class)))
                .thenAnswer(inv -> inv.getArgument(0));

        TaskResponse response = taskService.createTask(request);

        assertEquals("Test Task", response.getTitle());
        assertEquals("OPEN",response.getStatus());
    }
}
