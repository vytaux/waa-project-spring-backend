package com.theateam.waaprojectspringbackend.controller;

import com.theateam.waaprojectspringbackend.entity.Offer;
import com.theateam.waaprojectspringbackend.entity.Property;
import com.theateam.waaprojectspringbackend.entity.dto.request.PropertyRequestDto;
import com.theateam.waaprojectspringbackend.repository.OfferRepo;
import com.theateam.waaprojectspringbackend.service.OfferService;
import com.theateam.waaprojectspringbackend.service.PropertyService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/owners")
@CrossOrigin
@RequiredArgsConstructor
public class OwnersController {

    private final OfferRepo offerRepo;
    private final OfferService offerService;
    private final PropertyService propertyService;

    @PostMapping("/properties")
    public void createProperty(@RequestBody PropertyRequestDto propertyRequestDto) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        propertyService.create(authentication.getName(), propertyRequestDto);
    }

    @PutMapping("/properties/{propertyId}")
    public void updateProperty(@PathVariable Long propertyId, @RequestBody PropertyRequestDto propertyRequestDto) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        propertyService.update(authentication.getName(), propertyId, propertyRequestDto);
    }

    @GetMapping("/properties")
    public List<Property> getAllProperties() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return propertyService.findAllByOwnerEmail(authentication.getName());
    }

    @PutMapping("/properties/{propertyId}/turnContingent")
    public void turnPropertyContingent(@PathVariable Long propertyId) {
        // TODO if we already have 1 accepted, refuse
        // TODO disable buttons in frontend if theres 1 offer accepted
        propertyService.turnPropertyContingent(propertyId);
    }

    @PutMapping("/properties/{propertyId}/cancelContingency")
    public void cancelPropertyContingency(@PathVariable Long propertyId) {
        propertyService.cancelPropertyContingency(propertyId);
    }

    @DeleteMapping("/properties/{propertyId}")
    public String deleteProperty() {
        return "Greetings from Owners deleteProperty!";
    }

    @GetMapping("/offers")
    public List<Offer> offers() {
        return offerRepo.findAll();
    }

    @PutMapping("/offers/{offerId}/accept")
    public void acceptOffer(@PathVariable Long offerId) {
        // TODO if we already have 1 accepted, refuse
        // TODO disable buttons in frontend if theres 1 offer accepted
        offerService.acceptOffer(offerId);
    }

    @PutMapping("/offers/{offerId}/reject")
    public void rejectOffer(@PathVariable Long offerId) {
        offerService.rejectOffer(offerId);
    }

    @PutMapping("/messages")
    public String messages() {
        return "Greetings from Owners messages!";
    }
}
