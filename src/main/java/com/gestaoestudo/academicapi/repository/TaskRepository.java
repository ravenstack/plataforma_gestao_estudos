package com.gestaoestudo.academicapi.repository;

import com.gestaoestudo.academicapi.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findBySubjectId(Long subjectId);
}