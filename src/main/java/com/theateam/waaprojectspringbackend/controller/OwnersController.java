package com.theateam.waaprojectspringbackend.controller;

import com.theateam.waaprojectspringbackend.entity.Property;
import com.theateam.waaprojectspringbackend.entity.dto.request.PropertyRequestDto;
import com.theateam.waaprojectspringbackend.entity.dto.response.OfferResponseDto;
import com.theateam.waaprojectspringbackend.service.OfferService;
import com.theateam.waaprojectspringbackend.service.impl.PropertyServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/owners")
@CrossOrigin
@RequiredArgsConstructor
public class OwnersController {

    private final OfferService offerService;
    private final PropertyServiceImpl propertyService;

    @PostMapping("/properties")
    public ResponseEntity<String> createProperty(@RequestBody PropertyRequestDto propertyRequestDto) {
        return  propertyService.create(propertyRequestDto);
    }

    @PutMapping("/properties/{propertyId}")
    public void updateProperty(@PathVariable Long propertyId, @RequestBody PropertyRequestDto propertyRequestDto) {
        propertyService.update(propertyId, propertyRequestDto);
    }

    @GetMapping("/properties")
    public List<Property> getAllProperties() {
        return propertyService.findAllByOwnerEmail();
    }

    @PutMapping("/properties/{propertyId}/turnContingent")
    public void turnPropertyContingent(@PathVariable Long propertyId) {
        propertyService.turnPropertyContingent(propertyId);
    }

    @PutMapping("/properties/{propertyId}/cancelContingency")
    public void cancelPropertyContingency(@PathVariable Long propertyId) {
        propertyService.cancelPropertyContingency(propertyId);
    }

    @DeleteMapping("/properties/{propertyId}")
    public void deleteProperty(@PathVariable Long propertyId) {
        propertyService.delete(propertyId);
    }

    @GetMapping("/offers")
    public List<OfferResponseDto> offers() {
        return offerService.getOffersByOwnerEmail();
    }

    @PutMapping("/offers/{offerId}/accept")
    public void acceptOffer(@PathVariable Long offerId) {
        offerService.acceptOffer(offerId);
    }

    @PutMapping("/offers/{offerId}/reject")
    public void rejectOffer(@PathVariable Long offerId) {
        offerService.rejectOffer(offerId);
    }

    @PutMapping("/properties/{id}/sellProperty")
    public void sellProperty(@PathVariable Long id){
        offerService.sellProperty(id);
    }
}
