package com.ian.todo.service;

import com.ian.todo.dto.CreateUserDto;
import com.ian.todo.dto.TaskDataDto;
import com.ian.todo.dto.UpdateUserDataDto;
import com.ian.todo.dto.UserDataDto;
import com.ian.todo.exception.ItemNotFound;
import com.ian.todo.model.Task;
import com.ian.todo.model.User;
import com.ian.todo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository repository;

    @Autowired
    UserService(UserRepository repository) {
        this.repository = repository;
    }

    public List<User> findAll() {
        return repository.findAll();
    }

    public UserDataDto findById(Long id) throws ItemNotFound {

        Optional<User> dbUser = this.repository.findById(id);

        if (dbUser.isPresent()) {

            UserDataDto user;
            user = new UserDataDto(
                    dbUser.get().getUserName(),
                    dbUser.get().getFullName(),
                    dbUser.get().getTasks()
            );

            return user;

        } else {
            throw new ItemNotFound("User not found");
        }
    }

    public UserDataDto update(Long id, UpdateUserDataDto request) throws ItemNotFound {

        Optional<User> dbUser = this.repository.findById(id);
        UpdateUserDataDto user;

        if (dbUser.isPresent()) {

            user = new UpdateUserDataDto(
                    request.getUserName(),
                    request.getPassword(),
                    request.getFullName()
            );

            this.repository.update(id, user);

        } else {
            throw new ItemNotFound("User to update not found");
        }

        return new UserDataDto(
                dbUser.get().getUserName(),
                dbUser.get().getFullName(),
                dbUser.get().getTasks()
        );

    }

    public void delete(Long id) throws ItemNotFound {
        Optional<User> dbUser = repository.findById(id);
        if (dbUser.isPresent()) {
            User user = dbUser.get();
            repository.delete(user);
        } else {
            throw new ItemNotFound("User to delete not found");
        }
    }

    public void create(CreateUserDto user) {

        List<Task> tasks = List.of();

        User dbUser = new User(
                user.getUserName(),
                user.getPassword()
        );


        this.repository.save(dbUser);
    }

}
