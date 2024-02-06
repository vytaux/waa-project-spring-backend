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

        offerRepo.save(offer);
    }
}
