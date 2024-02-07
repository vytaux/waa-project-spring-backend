package com.theateam.waaprojectspringbackend.repository;

import com.theateam.waaprojectspringbackend.entity.Message;
import com.theateam.waaprojectspringbackend.entity.MessageSession;
import com.theateam.waaprojectspringbackend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<User, Long> {
    boolean existsByEmail(String email);
    User findByEmail(String email);

    @Query("select u from User u where u.email = :email")
    Optional<User> findOptionalByEmail(String email);
}