package com.ian.todo.dto;


import com.ian.todo.model.Task;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Set;

@AllArgsConstructor
@Getter
@Setter
public class UserDataDto {

    private String userName;
    private String fullName;
    private Set<Task> tasks;

}
