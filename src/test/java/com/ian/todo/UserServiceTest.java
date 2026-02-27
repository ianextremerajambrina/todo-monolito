package com.ian.todo;

import com.ian.todo.model.User;
import com.ian.todo.repository.UserRepository;
import com.ian.todo.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    @MockitoBean
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService; // tu capa de negocio

    @Test
    void shouldCreateUser() throws SQLException {
        User user = new User(
                "mundocaotico",
                "1234"
        );
        when(userRepository.save(user)).thenReturn(user);

        assertNotNull(user);
        assertEquals("mundocaotico", user.getUserName());
        verify(userRepository).save(user);
    }

}
