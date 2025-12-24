package com.taskflow.TaskFlowApplication.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;


@Service
public class NotificationService {
    private static final Logger log =
             LoggerFactory.getLogger(NotificationService.class);

    @Async("taskExecutor")
    public void sendTaskNotification(Long taskId){
        log.info("Async notification started for task {}", taskId);

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e){
            Thread.currentThread().interrupt();
        }

        log.info("Async notification completed for task {}", taskId);

    }
}

