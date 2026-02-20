package com.ian.todo.controller;

import com.ian.todo.dto.CreateUserDto;
import com.ian.todo.dto.UpdateUserDataDto;
import com.ian.todo.dto.UserDataDto;
import com.ian.todo.exception.ItemNotFound;
import com.ian.todo.model.Task;
import com.ian.todo.model.User;
import com.ian.todo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {

    private UserService userService;

    @Autowired
    UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("")
    public ResponseEntity<UserDataDto> create(@RequestBody CreateUserDto request) {
        List<Task> tasks = List.of();

        UserDataDto user = new UserDataDto(
                request.getFullName(),
                tasks
        );

        this.userService.create(request);

        return ResponseEntity.ok(user);
    }

    @GetMapping("{userId}")
    public ResponseEntity<UserDataDto> find(@PathVariable Long userId) throws ItemNotFound {
        return ResponseEntity.ok(this.userService.findById(userId));

    }

    @PutMapping("{userId}")
    public ResponseEntity<UserDataDto> modify(@PathVariable Long userId, @RequestBody UpdateUserDataDto request) throws ItemNotFound {
        this.userService.update(userId, request);
        // TODO: Implementar funcion
        return null;
    }

    @DeleteMapping("{userId}")
    public void delete(@PathVariable Long userId) throws ItemNotFound {
        this.userService.delete(userId);
    }

}
