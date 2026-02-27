package com.ian.todo.dto;


import com.ian.todo.model.Task;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Set;

@Getter
@Setter
public class UserDataDto {

    private String userName;
    private String fullName;
    private Set<Task> tasks;
    private Long id;

    public UserDataDto(String userName, String fullName, Set<Task> tasks) {
        this.userName = userName;
        this.fullName = fullName;
        this.tasks = tasks;
    }

    public UserDataDto(String userName) {
        this.userName = userName;
    }

}
