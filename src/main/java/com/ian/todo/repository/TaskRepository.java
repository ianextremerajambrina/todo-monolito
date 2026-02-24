package com.ian.todo.repository;

import com.ian.todo.dto.TaskDataDto;
import com.ian.todo.model.Task;
import jakarta.transaction.Transactional;
import org.hibernate.annotations.SQLSelect;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

    @Modifying
    @Transactional
    @Query("UPDATE Task t SET t.title = :#{#task.title}, t.description = :#{#task.description} WHERE t.id = :id")
    void update(Long id, TaskDataDto task);

    // TODO: Es esto lo mismo a findByReference ya incluido??
    @Query("SELECT t FROM Task t WHERE t.author.id = :authorId")
    Set<Task> findByAuthorId(Long authorId);

}
