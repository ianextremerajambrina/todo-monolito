package com.ian.todo.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;
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
    private String title;
    @Column()
    private String description;
    @Column()
    private String status;
    @ManyToOne()
    private User author; // Creador de la tarea
    @Column()
    private Date creationDate;
    @Column()
    private Date updatedDate;
    @Column()
    private Date dueDate;

    Task(String title, String description, User author) {
        this.title = title;
        this.description = description;
        this.author = author;
    }

}
