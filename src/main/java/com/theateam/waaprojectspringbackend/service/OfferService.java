package com.theateam.waaprojectspringbackend.service;

import com.theateam.waaprojectspringbackend.entity.*;
import com.theateam.waaprojectspringbackend.entity.dto.request.CreateOfferDto;

import java.util.List;

public interface OfferService {

    void createOffer(String username, CreateOfferDto createOfferDto);

    List<Offer> getAllOfferByCustomerId(Long userId);

    List<Offer> getOfferByStatusAndCustomer(Long customerId, OfferStatus status);
}
