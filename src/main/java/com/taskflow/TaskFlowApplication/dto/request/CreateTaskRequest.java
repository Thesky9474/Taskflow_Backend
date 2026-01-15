package com.taskflow.TaskFlowApplication.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateTaskRequest {

    @NotBlank
    private String title;

    private String description;

    @NotBlank
    private String status;

    private String priority; // HIGH, MEDIUM, LOW - defaults to MEDIUM if null

    @NotNull
    private Long userId;
}
