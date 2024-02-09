package com.theateam.waaprojectspringbackend.repository;

import com.theateam.waaprojectspringbackend.entity.Offer;
import com.theateam.waaprojectspringbackend.entity.OfferStatus;
import com.theateam.waaprojectspringbackend.entity.User;
import com.theateam.waaprojectspringbackend.entity.UserStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OfferRepo extends JpaRepository<Offer, Long> {

    List<Offer> findAllByCustomer_Id(Long customerId);
    List<Offer> findAllByCustomerAndStatus(User customer, OfferStatus status);

    @Query("SELECT o FROM Offer o WHERE o.property.owner.email = :name")
    List<Offer> getOffersByOwnerEmail(String name);

    Offer findOfferById(Long offerId);
}