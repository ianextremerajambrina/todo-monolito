package com.ian.todo.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column()
    private String userName;
    @Column()
    private String password;
    @Column()
    private String fullName;
    @OneToMany()
    private List<Task> tasks;

    public User(String userName, String password) {
        this.userName = userName;
        this.password = password;
        this.fullName = userName;
    }

}
