package com.ian.todo.dto;


import com.ian.todo.model.Task;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class UserDataDto {

    private String userName;
    private String email;
    private String fullName;
    private Set<Task> tasks;
    private Long id;

    public UserDataDto(Long id, String userName, String email, String fullName, Set<Task> tasks) {
        this.id = id;
        this.userName = userName;
        this.email = email;
        this.fullName = fullName;
        this.tasks = tasks;
    }

    public UserDataDto(String userName, String email, String fullName, Set<Task> tasks) {
        this.userName = userName;
        this.email = email;
        this.fullName = fullName;
        this.tasks = tasks;
    }

    public UserDataDto(String userName, String email) {
        this.userName = userName;
        this.email = email;
    }

    public UserDataDto(Long id, String userName, String email) {
        this.id = id;
        this.userName = userName;
        this.email = email;
    }

}
