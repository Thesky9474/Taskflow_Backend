package com.taskflow.TaskFlowApplication.dto.request;

import com.taskflow.TaskFlowApplication.entity.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class RegisterRequest {

    @NotBlank(message = "Name is required!!")
    private String name;

    @Email(message = "Invalid Email Format!")
    @NotBlank(message = "Email is required!!")
    private String email;

    @NotBlank(message = "Password is required!")
    private String password;

//    Optional:default user if not provided
    private Role role;
}
