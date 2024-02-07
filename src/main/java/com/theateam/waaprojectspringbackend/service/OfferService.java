package com.theateam.waaprojectspringbackend.service;

import com.theateam.waaprojectspringbackend.entity.Offer;
import com.theateam.waaprojectspringbackend.entity.OfferStatus;
import com.theateam.waaprojectspringbackend.entity.dto.request.CreateOfferDto;
import com.theateam.waaprojectspringbackend.entity.dto.request.UpdateOfferDto;
import com.theateam.waaprojectspringbackend.entity.dto.response.OfferResponseDto;

import java.util.List;

public interface OfferService {

    List<Offer> getAllOfferByCustomerId(Long userId);
    List<Offer> getOfferByStatusAndCustomer(Long customerId, OfferStatus status);

    void createOffer(String username, CreateOfferDto createOfferDto);
    void acceptOffer(Long offerId);
    void rejectOffer(Long offerId);
    List<OfferResponseDto> getOffers(String name);
    void cancelOffer(String name, Long offerId);
    void updateOffer(String name, Long offerId, UpdateOfferDto updateOfferDto);
}