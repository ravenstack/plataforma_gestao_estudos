package com.gestaoestudo.academicapi.controller;

import com.gestaoestudo.academicapi.entity.Subject;
import com.gestaoestudo.academicapi.entity.Task;
import com.gestaoestudo.academicapi.service.AcademicService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/academic")
@RequiredArgsConstructor
public class AcademicController {
    private final AcademicService service;

    @PostMapping("/subjects")
    public Subject createSubject(@RequestBody Subject s) { return service.saveSubject(s); }

    @PostMapping("/tasks")
    public Task createTask(@RequestParam Long subjectId, @RequestBody Task t) {
        return service.saveTask(subjectId, t);
    }

    @GetMapping("/subjects")
    public List<Subject> getAll() { return service.listAll(); }
}