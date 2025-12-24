package com.taskflow.TaskFlowApplication.scheduler;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TaskJobScheduler {

    private static final Logger log =
            LoggerFactory.getLogger(TaskJobScheduler.class);

    @Scheduled(cron = "0 0 0 * * ?")
    public void dailyTaskCheck() {
        log.info("Running daily scheduled task check");
    }
}

