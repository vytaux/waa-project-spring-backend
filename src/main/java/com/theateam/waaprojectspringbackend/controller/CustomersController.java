package com.theateam.waaprojectspringbackend.controller;

import com.theateam.waaprojectspringbackend.entity.Offer;
import com.theateam.waaprojectspringbackend.entity.OfferStatus;
import com.theateam.waaprojectspringbackend.entity.User;
import com.theateam.waaprojectspringbackend.entity.dto.request.CreateOfferDto;
import com.theateam.waaprojectspringbackend.entity.dto.request.SavePropertyRequestDto;
import com.theateam.waaprojectspringbackend.entity.dto.request.UpdateOfferDto;
import com.theateam.waaprojectspringbackend.entity.dto.response.OfferResponseDto;
import com.theateam.waaprojectspringbackend.entity.dto.response.PropertyResponseDto;
import com.theateam.waaprojectspringbackend.service.OfferService;
import com.theateam.waaprojectspringbackend.service.UserService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/customers")
@CrossOrigin
public class CustomersController {

    private final OfferService offerService;
    private final UserService userService;
    private final ModelMapper modelMapper;

    @GetMapping("/offers")
    public List<OfferResponseDto> offersHistory() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return offerService.getOffers(authentication.getName());
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
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        offerService.createOffer(authentication.getName(), createOfferDto);
    }

    @PutMapping("/offers/{offerId}")
    public void updateOffer(@PathVariable Long offerId, @RequestBody UpdateOfferDto updateOfferDto) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        offerService.updateOffer(authentication.getName(), offerId, updateOfferDto);
    }

    @GetMapping("/0/saved-properties")
    public List<PropertyResponseDto> getSavedProperties() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.getUserByEmail(authentication.getName()).orElseThrow();

        return user.getSavedProperties().stream()
                .map(property -> modelMapper.map(property, PropertyResponseDto.class))
                .toList();
    }

    @PutMapping("/0/saved-properties")
    public void addToSavedPropertiesList(@RequestBody SavePropertyRequestDto savePropertyRequestDto) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        userService.addToSavedPropertiesList(authentication.getName(), savePropertyRequestDto);
    }

    @DeleteMapping("/0/saved-properties/{itemId}")
    public void removePropertyFromSavedList(@PathVariable Long itemId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        userService.removePropertyFromSavedList(authentication.getName(), itemId);
    }

    @PostMapping("/messages/{propertyId}")
    public String messages() {
        return "Greetings from Customers messages/propId!";
    }

    @PutMapping("/offers/{offerId}/cancel")
    public void cancelOffer(@PathVariable Long offerId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        offerService.cancelOffer(authentication.getName(), offerId);
    }

    @GetMapping("/offers/placed")
    public String getPlacedOffers() {
        return "Greetings from Customers placedOffers!";
    }
}
