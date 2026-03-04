package com.ian.todo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class UpdateUserDataDto {

    private String userName;
    private String email;
    private String password;
    private String fullName;

}
