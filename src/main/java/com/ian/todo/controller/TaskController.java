package com.ian.todo.controller;

import com.ian.todo.dto.TaskDataDto;
import com.ian.todo.exception.ItemNotFound;
import com.ian.todo.model.Task;
import com.ian.todo.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    private final TaskService taskService;

    @Autowired
    TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping("")
    public ResponseEntity<List<TaskDataDto>> findAll() throws ItemNotFound{
        return ResponseEntity.ok(taskService.findAll());
    }

    @GetMapping("/author/{authorId}")
    public ResponseEntity<Set<TaskDataDto>> findByAuthorId(@PathVariable Long authorId) throws ItemNotFound {
        return ResponseEntity.ok(taskService.findByAuthorId(authorId));
    }

    @GetMapping("{taskId}")
    public ResponseEntity<TaskDataDto> find(@PathVariable Long taskId) throws ItemNotFound {
        return ResponseEntity.ok(this.taskService.findById(taskId));
    }

    @PostMapping()
    public ResponseEntity<TaskDataDto> create(@RequestBody TaskDataDto request) throws SQLException {
        return ResponseEntity.ok(this.taskService.create(request));
    }

    @PutMapping("{taskId}")
    public ResponseEntity<TaskDataDto> update(@PathVariable Long taskId, @RequestBody TaskDataDto request) throws ItemNotFound {
        return ResponseEntity.ok(this.taskService.update(taskId, request));
    }

    @DeleteMapping("{taskId}")
    public void delete(@PathVariable Long taskId) throws ItemNotFound {
        this.taskService.delete(taskId);

    }

}
