package com.taskflow.TaskFlowApplication.service;

import com.taskflow.TaskFlowApplication.dto.request.CreateUserRequest;
import com.taskflow.TaskFlowApplication.dto.response.UserResponse;
import com.taskflow.TaskFlowApplication.entity.User;

import java.util.List;

public interface UserService {
    UserResponse createUser(CreateUserRequest request);
    List<UserResponse> getAllUsers();
}
