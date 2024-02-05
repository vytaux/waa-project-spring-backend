package com.theateam.waaprojectspringbackend.repository;

import com.theateam.waaprojectspringbackend.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepo extends JpaRepository<Role, Long> {
    Role findByName(Role.RoleName name);
}