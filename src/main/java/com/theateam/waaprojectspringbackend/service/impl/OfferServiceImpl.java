package com.theateam.waaprojectspringbackend.service.impl;

import com.theateam.waaprojectspringbackend.entity.*;
import com.theateam.waaprojectspringbackend.entity.dto.request.CreateOfferDto;
import com.theateam.waaprojectspringbackend.entity.dto.request.UpdateOfferDto;
import com.theateam.waaprojectspringbackend.entity.dto.response.OfferResponseDto;
import com.theateam.waaprojectspringbackend.repository.OfferRepo;
import com.theateam.waaprojectspringbackend.repository.PropertyRepo;
import com.theateam.waaprojectspringbackend.repository.UserRepo;
import com.theateam.waaprojectspringbackend.service.EmailService;
import com.theateam.waaprojectspringbackend.service.OfferService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.scheduling.annotation.Async;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class OfferServiceImpl implements OfferService {

    private final OfferRepo offerRepo;
    private final UserRepo userRepo;
    private final ModelMapper modelMapper;
    private final PropertyRepo propertyRepo;
    private final EmailService emailService;

    @Async
    public void createOffer(CreateOfferDto createOfferDto) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = userRepo.findByEmail(authentication.getName()).orElseThrow();

        List<PropertyStatus> statuses = List.of(PropertyStatus.STATUS_AVAILABLE, PropertyStatus.STATUS_PENDING);
        Property property = propertyRepo
                .findByIdAndStatusIn(createOfferDto.getPropertyId(), statuses)
                .orElseThrow();

        Offer offer = modelMapper.map(createOfferDto, Offer.class);
        // for some reason maps propertyId -> id???????????
        offer.setId(null);
        offer.setProperty(property);
        offer.setCustomer(user);
        offer.setStatus(OfferStatus.STATUS_NEW);

        offerRepo.save(offer);

        // send email to owner
        emailService.sendEmail(
            property.getOwner().getEmail(),
            "You just received a new offer for your property \"" + property.getName() + "\"!",
            "You have a new offer for your property \""
                    + property.getName()
                    + "\" for $"
                    + offer.getPrice()
                    + ". Please check your account for more details."
        );
    }

    public void acceptOffer(Long offerId) {
        Offer offer = offerRepo.findById(offerId).orElseThrow();
        // Does this property already have an accepted offer?
        Property property = offer.getProperty();
        // Ideal way is probably add acceptedOffer to property
        // and property.getAcceptedOffer() != null
        Optional<Offer> acceptedOffer = property.getOffers().stream()
                .filter(o -> o.getStatus().equals(OfferStatus.STATUS_ACCEPTED))
                .findFirst();
        if (acceptedOffer.isPresent()) {
            return;
        }

        // All good
        offer.setStatus(OfferStatus.STATUS_ACCEPTED);
        offerRepo.save(offer);
        // Now turn property pending
        property.setStatus(PropertyStatus.STATUS_PENDING);
        propertyRepo.save(property);

        // Send email to customer
//        emailService.sendEmail(
//            offer.getCustomer().getEmail(),
//            "Your offer for property \"" + property.getName() + "\" has been accepted!",
//            "Your offer for property \""
//                    + property.getName()
//                    + "\" for $"
//                    + offer.getPrice()
//                    + " has been accepted. Please check your account for more details."
//        );
    }

    public void rejectOffer(Long offerId) {
        Offer offer = offerRepo.findById(offerId).orElseThrow();
        offer.setStatus(OfferStatus.STATUS_REJECTED);
        offerRepo.save(offer);
    }

    public void cancelOffer(Long offerId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Offer offer = offerRepo.findById(offerId).orElseThrow();
        if (!offer.getCustomer().getEmail().equals(authentication.getName())) {
            return;
        }
        // Cannot modify offer after ‘contingency’
        if (offer.getProperty().getStatus().equals(PropertyStatus.STATUS_CONTINGENT)) {
            return;
        }

        offer.setStatus(OfferStatus.STATUS_CANCELLED);
        offerRepo.save(offer);
    }

    public void updateOffer(Long offerId, UpdateOfferDto updateOfferDto) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Offer offer = offerRepo.findById(offerId).orElseThrow();
        //  Don't allow to update if it's not auth user
        if (!offer.getCustomer().getEmail().equals(authentication.getName())) {
            return;
        }
        // Don't allow to update if it's already accepted
        if (offer.getStatus().equals(OfferStatus.STATUS_ACCEPTED)) {
            return;
        }
        // Cannot modify offer after ‘contingency’
        if (offer.getProperty().getStatus().equals(PropertyStatus.STATUS_CONTINGENT)) {
            return;
        }

        modelMapper.map(updateOfferDto, offer);
        offerRepo.save(offer);
    }

    public List<OfferResponseDto> getOffers() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = userRepo.findByEmail(authentication.getName()).orElseThrow();
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
    public List<OfferResponseDto> getOffersByOwnerEmail() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        List<Offer> ownerOffers = offerRepo.getOffersByOwnerEmail(authentication.getName());
        return ownerOffers.stream()
                .map(offer -> modelMapper.map(offer, OfferResponseDto.class))
                .toList();
    }

    public void turnPropertyContingentForCustomer(Long offerId){
        Offer offer = offerRepo.findById(offerId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Offer with " + offerId + " Not Found"));
        if(offer.getStatus()!=OfferStatus.STATUS_ACCEPTED){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"OfferStatus is not Accepted");
        }
        Property property = offer.getProperty();
        if(property.getStatus()==PropertyStatus.STATUS_CONTINGENT){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Property status is already CONTINGENT");
        }
        property.setStatus(PropertyStatus.STATUS_CONTINGENT);
        propertyRepo.save(property);
    }

    @Override
    public void sellProperty(Long id) {
        Property property = propertyRepo.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Property with " + id + " Not Found"));
        if(property.getStatus()!=PropertyStatus.STATUS_CONTINGENT){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Property status is not CONTINGENT");
        }
        property.setStatus(PropertyStatus.STATUS_SOLD);
        propertyRepo.save(property);

        // send email to owner
        emailService.sendEmail(
                property.getOwner().getEmail(),
                "SOLD !",
                "Congrats, you sold. Please check your account for more details."
        );
    }
}
