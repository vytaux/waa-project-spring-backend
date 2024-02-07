package com.theateam.waaprojectspringbackend.controller;

import com.theateam.waaprojectspringbackend.entity.dto.request.CreateOfferDto;
import com.theateam.waaprojectspringbackend.entity.dto.response.OfferResponseDto;
import com.theateam.waaprojectspringbackend.repository.UserRepo;
import com.theateam.waaprojectspringbackend.service.OfferService;
import com.theateam.waaprojectspringbackend.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/customers")
@CrossOrigin
public class CustomersController {

    private final OfferService offerService;
    private final UserRepo userRepo;
    private final UserService userService;

    @GetMapping("/offers")
    public List<OfferResponseDto> offersHistory() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return offerService.getOffers(authentication.getName());
    }

    @GetMapping("/offers/current")
    public String offersCurrent() {
        return "Greetings from Customers offers/current!";
    }

    @PostMapping("/offers")
    public void createOffer(@RequestBody CreateOfferDto createOfferDto) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        offerService.createOffer(authentication.getName(), createOfferDto);
    }

    @GetMapping("/saved-properties")
    public String getSavedProperties() {
        return "Greetings from Customers GET saved-properties!";
    }

    @PostMapping("/saved-properties")
    public String savedProperties() {
        return "Greetings from Customers POST saved-properties!";
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
