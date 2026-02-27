package com.ian.todo.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CreateUserDto {

    private String userName;
    private String password;
    private String fullName;

    public CreateUserDto(String userName, String password) {
        this.userName = userName;
        this.password = password;
        this.fullName = userName;
    }

}
