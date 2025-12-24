package com.taskflow.TaskFlowApplication.service;

import com.taskflow.TaskFlowApplication.dto.request.RegisterRequest;
import com.taskflow.TaskFlowApplication.dto.response.UserResponse;
import com.taskflow.TaskFlowApplication.entity.Role;
import com.taskflow.TaskFlowApplication.entity.User;
import com.taskflow.TaskFlowApplication.exception.BadRequestException;
import com.taskflow.TaskFlowApplication.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService{
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserResponse register(RegisterRequest request){
        if(userRepository.existsByEmail(request.getEmail())){
            throw new BadRequestException("Email Already registered");
        }

        User user = new User();
        user.setName(request.getName());
        user.setEmail(request.getEmail());

        user.setPassword(passwordEncoder.encode(request.getPassword()));

        user.setRole(
            request.getRole() != null ? request.getRole() : Role.USER
        );

        User savedUser = userRepository.save(user);

        return new UserResponse(
                savedUser.getId(),
                savedUser.getName(),
                savedUser.getEmail()
        );
    }
}
