package com.ian.todo.dto;


import com.ian.todo.model.Task;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@Getter
@Setter
public class UserDataDto {

    private String fullName;
    private List<Task> tasks;

}
