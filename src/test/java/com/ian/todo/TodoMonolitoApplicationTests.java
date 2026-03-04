package com.ian.todo;

import com.ian.todo.dto.UserDataDto;
import com.ian.todo.model.Task;
import com.ian.todo.model.User;
import com.ian.todo.repository.TaskRepository;
import com.ian.todo.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.Set;

import static org.mockito.Mockito.*;

@SpringBootTest
@AutoConfigureMockMvc
class TodoMonolitoApplicationTests {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    TaskRepository taskRepository = mock(TaskRepository.class);
    @MockitoBean
    UserRepository userRepository = mock(UserRepository.class);

    User user = new User("test@example.com", "mundocaotico", "1234");

    UserDataDto dbUser = new UserDataDto(
            user.getUserName(),
            user.getEmail()
    );

    @Test
    void contextLoads() {
    }

    @Test
    void shouldCreateAUser() throws Exception {
        User savedUser = user;
        when(this.userRepository.save(user)).thenReturn(savedUser);
    }

    @Test
    void shouldCreateATask() {
        Task task = new Task(
                "Probando",
                "Nueva tarea",
                user
        );
        when(this.taskRepository.save(task)).thenReturn(task);
    }

    @Test
    void shouldRetrieveAllTasks() {
        List<Task> tasks = taskRepository.findAll();
        when(taskRepository.findAll()).thenReturn(tasks);
    }

    @Test
    void shouldReturnTasksByAuthorId() {
        Long id = 1L;
        Set<Task> tasks = taskRepository.findByAuthorId(id);
        when(tasks).thenReturn(tasks);
    }

}
