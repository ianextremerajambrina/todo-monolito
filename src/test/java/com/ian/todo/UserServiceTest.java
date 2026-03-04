package com.ian.todo;

import com.ian.todo.dto.CreateUserDto;
import com.ian.todo.dto.LoginUserDto;
import com.ian.todo.dto.UserDataDto;
import com.ian.todo.exception.ItemNotFound;
import com.ian.todo.model.User;
import com.ian.todo.repository.UserRepository;
import com.ian.todo.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    @MockitoBean
    private UserRepository userRepository;

    @Mock
    @MockitoBean
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private UserService userService;

    @Test
    void shouldCreateUser() {
        User user = new User("test@example.com", "mundocaotico", "1234");
        when(userRepository.save(any(User.class))).thenReturn(user);

        CreateUserDto createUserDto = new CreateUserDto();
        createUserDto.setEmail("test@example.com");
        createUserDto.setUserName("mundocaotico");
        createUserDto.setPassword("1234");

        UserDataDto result = userService.create(createUserDto);

        assertNotNull(result);
        assertEquals("mundocaotico", result.getUserName());
        assertEquals("test@example.com", result.getEmail());
        verify(userRepository).save(any(User.class));
    }

    @Test
    void shouldLoginWithEmail() throws ItemNotFound {
        User user = new User("test@example.com", "mundocaotico", "encodedPassword");
        
        when(userRepository.findByEmail("test@example.com")).thenReturn(Optional.of(user));
        when(passwordEncoder.matches("1234", "encodedPassword")).thenReturn(true);

        LoginUserDto loginDto = new LoginUserDto();
        loginDto.setEmail("test@example.com");
        loginDto.setPassword("1234");

        UserDataDto result = userService.login(loginDto);

        assertNotNull(result);
        assertEquals("test@example.com", result.getEmail());
        assertEquals("mundocaotico", result.getUserName());
        verify(userRepository).findByEmail("test@example.com");
    }

    @Test
    void shouldFailLoginWithInvalidPassword() {
        User user = new User("test@example.com", "mundocaotico", "encodedPassword");
        
        when(userRepository.findByEmail("test@example.com")).thenReturn(Optional.of(user));
        when(passwordEncoder.matches("wrongPassword", "encodedPassword")).thenReturn(false);

        LoginUserDto loginDto = new LoginUserDto();
        loginDto.setEmail("test@example.com");
        loginDto.setPassword("wrongPassword");

        assertThrows(ItemNotFound.class, () -> userService.login(loginDto));
    }

    @Test
    void shouldFailLoginWithNonExistentEmail() {
        when(userRepository.findByEmail("nonexistent@example.com")).thenReturn(Optional.empty());

        LoginUserDto loginDto = new LoginUserDto();
        loginDto.setEmail("nonexistent@example.com");
        loginDto.setPassword("1234");

        assertThrows(ItemNotFound.class, () -> userService.login(loginDto));
    }

}
