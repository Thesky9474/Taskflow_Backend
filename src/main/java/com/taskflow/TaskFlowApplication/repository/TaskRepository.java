package com.taskflow.TaskFlowApplication.repository;

import com.taskflow.TaskFlowApplication.entity.Task;
import lombok.extern.java.Log;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface TaskRepository extends JpaRepository<Task, Long> {
    @Query("""
            SELECT t FROM Task t
            JOIN FETCH t.assignedTo
            WHERE t.assignedTo.id = :userId
""")
//    Page<Task> findByAssignedToId(Long userId, Pageable pageable);
    Page<Task> findTaskWithUser(@Param("userId") Long userId, Pageable pageable);
}
