package com.ian.todo.dto;

import com.ian.todo.model.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class TaskDataDto {

    private String title;
    private String description;
    private String status;
    private User author;
}
