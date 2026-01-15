package com.taskflow.TaskFlowApplication.controller;

import com.taskflow.TaskFlowApplication.dto.request.CreateUserRequest;
import com.taskflow.TaskFlowApplication.dto.response.UserResponse;
import com.taskflow.TaskFlowApplication.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<UserResponse> createUser(@Valid @RequestBody CreateUserRequest request){
        return ResponseEntity.ok(userService.createUser(request));
    }

    @GetMapping
    public ResponseEntity<List<UserResponse>> getUser() {
        return ResponseEntity.ok(userService.getAllUsers());
    }
}
