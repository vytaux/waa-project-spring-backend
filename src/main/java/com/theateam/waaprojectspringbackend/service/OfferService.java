package com.theateam.waaprojectspringbackend.service;

import com.theateam.waaprojectspringbackend.entity.*;
import com.theateam.waaprojectspringbackend.entity.dto.request.CreateOfferDto;

import java.util.List;


public interface OfferService {

    List<Offer> getAllOfferByCustomerId(Long userId);

    List<Offer> getOfferByStatusAndCustomer(Long customerId, OfferStatus status);
    void createOffer(String username, CreateOfferDto createOfferDto);

    void acceptOffer(Long offerId);

    void rejectOffer(Long offerId);
}
