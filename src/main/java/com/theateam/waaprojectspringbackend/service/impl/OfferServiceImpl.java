package com.theateam.waaprojectspringbackend.service.impl;

import com.theateam.waaprojectspringbackend.entity.*;
import com.theateam.waaprojectspringbackend.entity.dto.request.CreateOfferDto;
import com.theateam.waaprojectspringbackend.entity.dto.request.UpdateOfferDto;
import com.theateam.waaprojectspringbackend.entity.dto.response.OfferResponseDto;
import com.theateam.waaprojectspringbackend.repository.OfferRepo;
import com.theateam.waaprojectspringbackend.repository.PropertyRepo;
import com.theateam.waaprojectspringbackend.repository.UserRepo;
import com.theateam.waaprojectspringbackend.service.OfferService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class OfferServiceImpl implements OfferService {

    private final OfferRepo offerRepo;
    private final UserRepo userRepo;
    private final ModelMapper modelMapper;
    private final PropertyRepo propertyRepo;

    public void createOffer(String username, CreateOfferDto createOfferDto) {
        User user = userRepo.findByEmail(username).orElseThrow();

        List<PropertyStatus> statuses = List.of(PropertyStatus.STATUS_AVAILABLE, PropertyStatus.STATUS_PENDING);
        Property property = propertyRepo
                .findByIdAndStatusIn(createOfferDto.getPropertyId(), statuses)
                .orElseThrow();

        Offer offer = modelMapper.map(createOfferDto, Offer.class);
        offer.setProperty(property);
        offer.setCustomer(user);
        offer.setStatus(OfferStatus.STATUS_NEW);

        offerRepo.save(offer);
    }

    public void acceptOffer(Long offerId) {
        Offer offer = offerRepo.findById(offerId).orElseThrow();
        offer.setStatus(OfferStatus.STATUS_ACCEPTED);
        offerRepo.save(offer);
        // Now turn property pending
        Property property = propertyRepo.findById(offer.getProperty().getId()).orElseThrow();
        property.setStatus(PropertyStatus.STATUS_PENDING);
        propertyRepo.save(property);
    }

    public void rejectOffer(Long offerId) {
        Offer offer = offerRepo.findById(offerId).orElseThrow();
        offer.setStatus(OfferStatus.STATUS_REJECTED);
        offerRepo.save(offer);
    }

    public void cancelOffer(String name, Long offerId) {
        Offer offer = offerRepo.findById(offerId).orElseThrow();
        if (!offer.getCustomer().getEmail().equals(name)) {
            throw new RuntimeException("Offer cannot be cancelled");
        }
        // Cannot cancel offer after ‘contingency’
        if (offer.getProperty().getStatus().equals(PropertyStatus.STATUS_CONTINGENT)) {
            throw new RuntimeException("Offer cannot be cancelled");
        }

        offer.setStatus(OfferStatus.STATUS_CANCELLED);
        offerRepo.save(offer);
    }

    public void updateOffer(String name, Long offerId, UpdateOfferDto updateOfferDto) {
        Offer offer = offerRepo.findById(offerId).orElseThrow();
        if (!offer.getCustomer().getEmail().equals(name)) {
            throw new RuntimeException("Offer cannot be updated");
        }
        if (offer.getStatus().equals(OfferStatus.STATUS_ACCEPTED)) {
            throw new RuntimeException("Offer cannot be updated");
        }

        modelMapper.map(updateOfferDto, offer);
        offerRepo.save(offer);
    }

    public List<OfferResponseDto> getOffers(String name) {
        User user = userRepo.findByEmail(name).orElseThrow();
        return user.getOffers().stream()
                .map(offer -> modelMapper.map(offer, OfferResponseDto.class))
                .toList();
    }

    public List<Offer> getAllOfferByCustomerId(Long customerId){
        return offerRepo.findAllByCustomer_Id(customerId);
    }

    @Override
    public List<Offer> getOfferByStatusAndCustomer(Long customerId, OfferStatus status) {
        User user = userRepo.findById(customerId).orElseThrow();
        return offerRepo.findAllByCustomerAndStatus(user, status);
    }

    @Override
    public List<OfferResponseDto> getOffersByOwnerEmail(String name) {
        List<Offer> ownerOffers = offerRepo.getOffersByOwnerEmail(name);
        return ownerOffers.stream()
                .map(offer -> modelMapper.map(offer, OfferResponseDto.class))
                .toList();
    }
}
