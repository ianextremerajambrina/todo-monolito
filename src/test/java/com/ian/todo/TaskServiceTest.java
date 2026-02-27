package com.ian.todo;

import com.ian.todo.dto.TaskDataDto;
import com.ian.todo.model.Task;
import com.ian.todo.model.User;
import com.ian.todo.repository.TaskRepository;
import com.ian.todo.service.TaskService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TaskServiceTest {

    @Mock
    private TaskRepository taskRepository;

    @InjectMocks
    private TaskService taskService; // tu capa de negocio

    @Test
    void shouldCreateTask() throws SQLException {
        Task task = new Task("Probando", "Nueva tarea", new User("mundocaotico", "1234"));
        when(taskRepository.save(task)).thenReturn(task);

        assertNotNull(task);
        assertEquals("Probando", task.getTitle());
        verify(taskRepository).save(task);
    }
}
