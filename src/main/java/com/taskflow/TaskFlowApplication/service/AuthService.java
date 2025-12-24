package com.taskflow.TaskFlowApplication.service;

import com.taskflow.TaskFlowApplication.dto.request.RegisterRequest;
import com.taskflow.TaskFlowApplication.dto.response.UserResponse;

public interface AuthService {
    UserResponse register(RegisterRequest request);
}
