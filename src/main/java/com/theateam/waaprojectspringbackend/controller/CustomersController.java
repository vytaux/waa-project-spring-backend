package com.theateam.waaprojectspringbackend.controller;

import com.theateam.waaprojectspringbackend.entity.Offer;
import com.theateam.waaprojectspringbackend.entity.OfferStatus;
import com.theateam.waaprojectspringbackend.entity.dto.request.CreateOfferDto;
import com.theateam.waaprojectspringbackend.entity.dto.request.SavePropertyRequestDto;
import com.theateam.waaprojectspringbackend.entity.dto.request.UpdateOfferDto;
import com.theateam.waaprojectspringbackend.entity.dto.response.OfferResponseDto;
import com.theateam.waaprojectspringbackend.entity.dto.response.PropertyResponseDto;
import com.theateam.waaprojectspringbackend.service.OfferService;
import com.theateam.waaprojectspringbackend.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/customers")
@CrossOrigin
public class CustomersController {

    private final OfferService offerService;
    private final UserService userService;

    @GetMapping("/offers")
    public List<OfferResponseDto> offersHistory() {
        return offerService.getOffers();
    }

    @GetMapping("{id}/offers/history")
    public List<Offer> getAllOffersHistory(@PathVariable Long id) {
        return offerService.getAllOfferByCustomerId(id);
    }

    @GetMapping("{id}/offers")
    public List<Offer> getOffersByStatusAndCustomer(
            @PathVariable Long id,
            @RequestParam("status") String status
    ) {
        return offerService.getOfferByStatusAndCustomer(id, OfferStatus.valueOf(status));
    }

    @PostMapping("/offers")
    public void createOffer(@RequestBody CreateOfferDto createOfferDto) {
        offerService.createOffer(createOfferDto);
    }

    @PutMapping("/offers/{offerId}")
    public void updateOffer(@PathVariable Long offerId, @RequestBody UpdateOfferDto updateOfferDto) {
        offerService.updateOffer(offerId, updateOfferDto);
    }

    @GetMapping("/0/saved-properties")
    public List<PropertyResponseDto> getSavedProperties() {
        return userService.getSavedProperties();
    }

    @PutMapping("/0/saved-properties")
    public void addToSavedPropertiesList(@RequestBody SavePropertyRequestDto savePropertyRequestDto) {
        userService.addToSavedPropertiesList(savePropertyRequestDto);
    }

    // TODO feels weird to delete an item, but sending in a separate dto when PUTting... 🤔
    // probably should send SavedPropertyRequestDto with propertyId because these are not
    // "real" entities, but just a list of ids
    @DeleteMapping("/0/saved-properties/{itemId}")
    public void removePropertyFromSavedList(@PathVariable Long itemId) {
        userService.removePropertyFromSavedList(itemId);
    }

    @PutMapping("/offers/{offerId}/cancel")
    public void cancelOffer(@PathVariable Long offerId) {
        offerService.cancelOffer(offerId);
    }

    @PutMapping("/offers/{offerId}/turnContingent")
    public void turnPropertyContingentForCustomer(@PathVariable Long offerId){
        offerService.turnPropertyContingentForCustomer(offerId);
    }

}
