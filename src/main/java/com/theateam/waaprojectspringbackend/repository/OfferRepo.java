package com.theateam.waaprojectspringbackend.repository;

import com.theateam.waaprojectspringbackend.entity.Offer;
import com.theateam.waaprojectspringbackend.entity.User;
import com.theateam.waaprojectspringbackend.entity.UserStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OfferRepo extends JpaRepository<Offer, Long> {

    List<Offer> findByCustomer_Id(int userId);
}