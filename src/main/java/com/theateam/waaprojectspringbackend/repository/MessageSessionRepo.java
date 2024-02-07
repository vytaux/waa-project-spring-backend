package com.theateam.waaprojectspringbackend.repository;

import com.theateam.waaprojectspringbackend.entity.MessageSession;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageSessionRepo extends JpaRepository<MessageSession, Integer> {

    @Query("select ms from MessageSession ms where ms.userOne.email = :email OR ms.userTwo.email = :email")
    List<MessageSession> findAllByUserEmail(String email);

    @Query("select ms from MessageSession ms where (ms.userOne.id = :userOneId AND ms.userTwo.id = :userTwoId) OR (ms.userOne.id = :userTwoId AND ms.userTwo.id = :userOneId)")
    MessageSession findByUserOneAndUserTwoId(long userOneId, long userTwoId);

}
