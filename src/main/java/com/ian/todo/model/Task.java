package com.ian.todo.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;
import java.time.LocalDate;

@Entity
@Table(name = "tasks")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column()
    @NotBlank(message = "Title is mandatory")
    @NotEmpty(message = "Title is mandatory")
    private String title;
    @Column()
    @NotBlank(message = "Description is mandatory")
    @NotEmpty(message = "Description is mandatory")
    private String description;
    @Column()
    private String status;
    @ManyToOne()
    @JoinColumn(name = "user_id")
    private User author; // Creador de la tarea
    @Column()
    private Date creationDate;
    @Column()
    private Date updatedDate;
    @Column()
    private Date dueDate;

    public Task(String title, String description, User author) {
        this.title = title;
        this.description = description;
        this.author = author;
        this.status = "CREATED";
        this.creationDate = Date.valueOf(LocalDate.now());
        this.dueDate = Date.valueOf(LocalDate.now());
    }

}
