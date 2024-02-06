package com.theateam.waaprojectspringbackend.service;

import com.theateam.waaprojectspringbackend.entity.*;
import com.theateam.waaprojectspringbackend.entity.dto.request.CreateOfferDto;
import com.theateam.waaprojectspringbackend.repository.OfferRepo;
import com.theateam.waaprojectspringbackend.repository.PropertyRepo;
import com.theateam.waaprojectspringbackend.repository.UserRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class OfferService {

    private final OfferRepo offerRepo;
    private final PropertyRepo propertyRepo;
    private final UserRepo userRepo;
    private final ModelMapper modelMapper;

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
}
