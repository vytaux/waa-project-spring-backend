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
    List<OfferResponseDto> getOffersByOwnerEmail();
    List<OfferResponseDto> getOffers();
    void createOffer(CreateOfferDto createOfferDto);
    void acceptOffer(Long offerId);
    void rejectOffer(Long offerId);
    void cancelOffer(Long offerId);
    void updateOffer(Long offerId, UpdateOfferDto updateOfferDto);
    void turnPropertyContingentForCustomer(Long offerId);
    void sellProperty(Long offerId);
}