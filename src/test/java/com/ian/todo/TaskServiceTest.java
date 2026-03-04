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

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TaskServiceTest {

    @Mock
    private TaskRepository taskRepository;

    @InjectMocks
    private TaskService taskService;

    @Test
    void shouldCreateTask() {
        User user = new User("test@example.com", "mundocaotico", "1234");
        
        TaskDataDto taskDataDto = new TaskDataDto();
        taskDataDto.setTitle("Probando");
        taskDataDto.setDescription("Nueva tarea");
        taskDataDto.setAuthor(user);

        when(taskRepository.save(any(Task.class))).thenReturn(new Task("Probando", "Nueva tarea", user));

        TaskDataDto result = taskService.create(taskDataDto);

        assertNotNull(result);
        assertEquals("Probando", result.getTitle());
        verify(taskRepository).save(any(Task.class));
    }
}
