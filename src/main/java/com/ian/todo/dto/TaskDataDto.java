package com.ian.todo.dto;

import com.ian.todo.model.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TaskDataDto {

    private String title;
    private String description;
    private String status;
    private User author;
}
