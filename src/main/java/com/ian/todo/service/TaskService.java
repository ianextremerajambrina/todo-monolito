package com.ian.todo.service;

import com.ian.todo.dto.TaskDataDto;
import com.ian.todo.exception.ItemNotFound;
import com.ian.todo.model.Task;
import com.ian.todo.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TaskService {

    private final TaskRepository repository;

    @Autowired
    TaskService(TaskRepository repository) {
        this.repository = repository;
    }

    public List<Task> findAll() {
        return repository.findAll();
    }

    public TaskDataDto findById(Long id) throws ItemNotFound {

        Optional<Task> dbTask = repository.findById(id);;

        if (dbTask.isPresent()) {
            TaskDataDto task = new TaskDataDto(
                    dbTask.get().getTitle(),
                    dbTask.get().getDescription(),
                    dbTask.get().getStatus(),
                    dbTask.get().getAuthor()
            );

            return task;

        } else {
            throw new ItemNotFound("Task not found");
        }

    }

    public TaskDataDto update(Long id, TaskDataDto request) throws ItemNotFound {

        Optional<Task> dbTask = this.repository.findById(id);

        if (dbTask.isPresent()) {
            TaskDataDto task = new TaskDataDto(
                    request.getTitle(),
                    request.getDescription(),
                    request.getStatus(),
                    request.getAuthor()

            );

            this.repository.update(id, task);

            return ResponseEntity.ok(task).getBody();

        } else {
            throw new ItemNotFound("Task to update not found");
        }

    }

    public void delete(Long id) throws ItemNotFound {
        Optional<Task> dbTask = repository.findById(id);
        if (dbTask.isPresent()) {
            Task task = dbTask.get();
            repository.delete(task);
        } else {
            throw new ItemNotFound("Task to delete not found");
        }
    }

    public List<TaskDataDto> findByAuthorId(Long authorId) throws ItemNotFound {
        List<Task> dbTasks = repository.findByAuthorId(authorId);

        if (dbTasks.isEmpty()) {
            throw new ItemNotFound("Tasks not found for the specified author");
        } else {

            return dbTasks.stream().map(t -> new TaskDataDto(
                    t.getTitle(),
                    t.getDescription(),
                    t.getStatus(),
                    t.getAuthor()
            )).collect(Collectors.toList());

        }

    }

}
