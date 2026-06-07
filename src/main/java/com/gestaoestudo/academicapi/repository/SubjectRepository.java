package com.gestaoestudo.academicapi.repository;

import com.gestaoestudo.academicapi.entity.Subject;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubjectRepository extends JpaRepository<Subject, Long> {}
