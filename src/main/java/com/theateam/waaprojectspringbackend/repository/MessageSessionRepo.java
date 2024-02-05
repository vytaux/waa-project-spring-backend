package com.theateam.waaprojectspringbackend.repository;

import com.theateam.waaprojectspringbackend.entity.MessageSession;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MessageSessionRepo extends JpaRepository<MessageSession, Integer> {
}
