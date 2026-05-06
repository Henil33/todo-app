package com.todo.backend.entity;

import jakarta.persistence.*;
import jakarta.persistence.Id;
import jdk.jfr.Enabled;

import java.time.LocalDateTime;

@Entity
public class Todo {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String title;

    private String decription;

    private boolean completed;

    private LocalDateTime created_at;
}
