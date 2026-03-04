package com.ian.todo.repository;

import com.ian.todo.dto.UpdateUserDataDto;
import com.ian.todo.model.User;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);

    @Modifying
    @Transactional
    @Query("UPDATE User u SET u.userName = :#{#user.userName}, u.email = :#{#user.email}, u.password = :#{#user.password}, u.fullName = :#{#user.fullName} WHERE u.id = :id")
    public void update(Long id, UpdateUserDataDto user);

}
