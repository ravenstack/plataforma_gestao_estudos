package com.gestaoestudo.academicapi.service;

import com.gestaoestudo.academicapi.entity.Subject;
import com.gestaoestudo.academicapi.entity.Task;
import com.gestaoestudo.academicapi.repository.SubjectRepository;
import com.gestaoestudo.academicapi.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AcademicService {
    private final SubjectRepository subjectRepo;
    private final TaskRepository taskRepo;

    public Subject saveSubject(Subject s) { return subjectRepo.save(s); }

    public Task saveTask(Long subjectId, Task t) {
        Subject s = subjectRepo.findById(subjectId).orElseThrow();
        t.setSubject(s);
        return taskRepo.save(t);
    }

    public List<Subject> listAll() { return subjectRepo.findAll(); }
}