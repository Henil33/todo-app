package com.todo.backend.controller;

import com.todo.backend.entity.Todo;
import com.todo.backend.repository.TodoRepository;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/todos")
public class HelloController {

    private final TodoRepository todoRepository;

    public HelloController(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    @GetMapping
    public List<Todo> getAllTodos() {
        return todoRepository.findAll();
    }
}