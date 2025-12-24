package com.taskflow.TaskFlowApplication.security;

import com.taskflow.TaskFlowApplication.entity.User;
import com.taskflow.TaskFlowApplication.exception.ResourceNotFoundException;
import com.taskflow.TaskFlowApplication.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email){
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("User Not Found!"));

        return new CustomUserDetails(user);
    }
}
