package com.ian.todo;

import com.ian.todo.dto.TaskDataDto;
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
import tools.jackson.databind.ObjectMapper;

import java.util.List;
import java.util.Set;

import static org.mockito.Mockito.*;
import static org.springframework.mock.http.server.reactive.MockServerHttpRequest.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class TodoMonolitoApplicationTests {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    TaskRepository taskRepository = mock(TaskRepository.class);
    @MockitoBean
    UserRepository userRepository = mock(UserRepository.class);

    UserDataDto dbUser = new UserDataDto(
            "mundocaotico",
            "Ian Extremera Jambrina",
            Set.of()
    );

    User user = new User(
            dbUser.getUserName(),
            "1234"
    );

    private static final ObjectMapper MAPPER = new ObjectMapper();

    private String toJson(Object obj) {
        return MAPPER.writeValueAsString(obj);
    }

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

        TaskDataDto dbTask = new TaskDataDto(
                "Probando",
                "Nueva tarea",
                "CREATED",
                user
        );

        Task task = new Task(
                dbTask.getTitle(),
                dbTask.getDescription(),
                dbTask.getAuthor()
        );

        when(this.taskRepository.save(task)).thenReturn(
                task
        );

    }

    @Test
    void shouldRetrieveAllTasks() {

        List<Task> tasks = taskRepository.findAll();

        when(taskRepository.findAll()).thenReturn(
                tasks
        );
    }

    @Test
    void shouldReturnTasksByAuthorId() {
        Long id = 1L;
        Set<Task> tasks = taskRepository.findByAuthorId(id);

        when(tasks).thenReturn(
                tasks
        );

    }

}
