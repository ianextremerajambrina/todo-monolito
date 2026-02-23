package com.ian.todo.controller;

import com.ian.todo.dto.TaskDataDto;
import com.ian.todo.exception.ItemNotFound;
import com.ian.todo.model.Task;
import com.ian.todo.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    private final TaskService taskService;

    @Autowired
    TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping("{authorId}")
    public ResponseEntity<List<TaskDataDto>> findAll(@PathVariable Long authorId) throws ItemNotFound {
        return ResponseEntity.ok(taskService.findByAuthorId(authorId));
    }

    @PostMapping("{taskId}")
    public ResponseEntity<TaskDataDto> find(@PathVariable Long taskId) throws ItemNotFound {
        return ResponseEntity.ok(this.taskService.findById(taskId));
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
