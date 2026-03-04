package com.ian.todo.service;

import ch.qos.logback.core.net.SyslogOutputStream;
import com.ian.todo.dto.CreateUserDto;
import com.ian.todo.dto.LoginUserDto;
import com.ian.todo.dto.UpdateUserDataDto;
import com.ian.todo.dto.UserDataDto;
import com.ian.todo.exception.InsertItemError;
import com.ian.todo.exception.ItemNotFound;
import com.ian.todo.model.User;
import com.ian.todo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    UserService(UserRepository repository, PasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
    }

    public List<User> findAll() {
        return repository.findAll();
    }

    public UserDataDto findById(Long id) throws ItemNotFound {
        Optional<User> dbUser = this.repository.findById(id);

        if (dbUser.isPresent()) {
            User user = dbUser.get();
            return new UserDataDto(
                    user.getUserName(),
                    user.getEmail(),
                    user.getFullName(),
                    user.getTasks()
            );
        } else {
            throw new ItemNotFound("User not found");
        }
    }

    public UserDataDto login(LoginUserDto loginDto) throws ItemNotFound {
        if (loginDto.getEmail() == null || loginDto.getEmail().isBlank()) {
            throw new ItemNotFound("Email is required");
        }

        Optional<User> user = repository.findByEmail(loginDto.getEmail());

        if (user.isEmpty()) {
            throw new ItemNotFound("User not found");
        }

        String encryptedPassword = user.get().getPassword();

        System.out.println(loginDto.getPassword());
        System.out.println(encryptedPassword);

        System.out.println(passwordEncoder.matches(loginDto.getPassword(), encryptedPassword));

        if (!passwordEncoder.matches(loginDto.getPassword(), user.get().getPassword())) {
            throw new ItemNotFound("Invalid password");
        }

        User u = user.get();
        return new UserDataDto(
                u.getId(),
                u.getUserName(),
                u.getEmail(),
                u.getFullName(),
                u.getTasks()
        );
    }

    public UserDataDto update(Long id, UpdateUserDataDto request) throws ItemNotFound {
        Optional<User> dbUser = this.repository.findById(id);
        String encryptedPassword;
        if (dbUser.isPresent()) {
            // Ciframos la contraseña para evitar que se guarde en texto claro
            encryptedPassword = passwordEncoder.encode(dbUser.get().getPassword());
            request.setPassword(encryptedPassword);

            // Acutalizamos el usuario con la contraseña cifrada
            this.repository.update(id, request);
            dbUser = this.repository.findById(id);
        } else {
            throw new ItemNotFound("User to update not found");
        }

        User u = dbUser.get();

        return new UserDataDto(
                u.getUserName(),
                u.getEmail(),
                u.getFullName(),
                u.getTasks()
        );
    }

    public void delete(Long id) throws ItemNotFound {
        Optional<User> dbUser = repository.findById(id);
        if (dbUser.isPresent()) {
            repository.delete(dbUser.get());
        } else {
            throw new ItemNotFound("User to delete not found");
        }
    }

    public UserDataDto create(CreateUserDto userDto) throws InsertItemError {
        try {
            String encodedPassword = passwordEncoder.encode(userDto.getPassword());

            User newUser = new User(
                    userDto.getEmail(),
                    userDto.getUserName(),
                    encodedPassword
            );

            User savedUser = this.repository.save(newUser);

            return new UserDataDto(
                    savedUser.getId(),
                    savedUser.getUserName(),
                    savedUser.getEmail()
            );
        } catch (Exception e) {
            throw new InsertItemError("Could not insert item in database");
        }
    }

}
