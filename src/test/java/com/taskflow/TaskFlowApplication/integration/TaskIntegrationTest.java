package com.taskflow.TaskFlowApplication.integration;

import com.taskflow.TaskFlowApplication.entity.Task;
import com.taskflow.TaskFlowApplication.repository.TaskRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.mysql.MySQLContainer;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@Testcontainers
@SpringBootTest
public class TaskIntegrationTest {

    @Container
    static MySQLContainer mysql =
            new MySQLContainer("mysql:8.0")
                    .withDatabaseName("testdb")
                    .withUsername("test")
                    .withPassword("test");

    @DynamicPropertySource
    static void configure(DynamicPropertyRegistry registry){
        registry.add("spring.datasource.url", mysql::getJdbcUrl);
        registry.add("spring.datasource.username", mysql::getUsername);
        registry.add("spring.datasource.password", mysql::getPassword);
    }

    @Autowired
    private TaskRepository taskRepository;

    @Test
    void shouldSaveTaskInDatabase() {
        Task task = new Task();
        task.setTitle("Integration Task");
        task.setStatus("OPEN");

        Task saved = taskRepository.save(task);

        assertNotNull(saved.getId());
    }
}
