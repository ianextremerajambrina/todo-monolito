package com.ian.todo.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
    @NotBlank(message = "Description is mandatory")
    @NotEmpty(message = "Description is mandatory")
    private String userName;
    @Column()
    @NotBlank(message = "Password is mandatory")
    @NotEmpty(message = "Password is mandatory")
    private String password;
    @Column()
    @NotBlank(message = "Full name is mandatory")
    @NotEmpty(message = "Full name is mandatory")
    private String fullName;
    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL)
    private Set<Task> tasks = new HashSet<>();

    public User(String userName, String password) {
        this.userName = userName;
        this.password = password;
        this.fullName = userName;
    }

}
