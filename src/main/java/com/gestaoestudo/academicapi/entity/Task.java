package com.gestaoestudo.academicapi.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String description;
    private boolean completed = false;

    @ManyToOne
    @JoinColumn(name = "subject_id")
    private Subject subject;
}
