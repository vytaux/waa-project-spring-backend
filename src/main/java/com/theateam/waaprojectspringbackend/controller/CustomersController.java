package com.theateam.waaprojectspringbackend.controller;

import com.theateam.waaprojectspringbackend.entity.Offer;
import com.theateam.waaprojectspringbackend.entity.OfferStatus;
import com.theateam.waaprojectspringbackend.entity.dto.request.CreateOfferDto;
import com.theateam.waaprojectspringbackend.exception.CustomException;
import com.theateam.waaprojectspringbackend.service.OfferService;
import com.theateam.waaprojectspringbackend.entity.User;
import com.theateam.waaprojectspringbackend.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    private final UserService userService;

    @GetMapping
    public List<User> getAllUsers(){
        return userService.getAllUser();
    }

    @GetMapping("{id}/offers/history")
    public List<Offer> getAllOffersHistory(@PathVariable Long id) {
        return offerService.getAllOfferByCustomerId(id);
    }

    @GetMapping("{id}/offers")
    public ResponseEntity<List<Offer>> getOffersByStatusAndCustomer(@PathVariable Long id, @RequestParam("status") String status) {
        try {
            List<Offer> offers = offerService.getOfferByStatusAndCustomer(id, OfferStatus.valueOf(status));
            return ResponseEntity.ok(offers);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
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
    public String cancelOffer() {
        return "Greetings from Customers cancelOffer!";
    }

    @GetMapping("/offers/placed")
    public String getPlacedOffers() {
        return "Greetings from Customers placedOffers!";
    }
}
