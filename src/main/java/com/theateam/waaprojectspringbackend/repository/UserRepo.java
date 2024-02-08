package com.theateam.waaprojectspringbackend.repository;

import com.theateam.waaprojectspringbackend.entity.RoleType;
import com.theateam.waaprojectspringbackend.entity.User;
import com.theateam.waaprojectspringbackend.entity.UserStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<User, Long> {
    boolean existsByEmail(String email);
    Optional<User> findByEmail(String email);

    @Query("SELECT u FROM User u JOIN u.roles r WHERE u.status = :status AND r.roleType = :roleType")
    List<User> findByStatusAndRoleType(UserStatus status, RoleType roleType);
}