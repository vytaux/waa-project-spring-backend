package com.theateam.waaprojectspringbackend.controller;

import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/customers")
@CrossOrigin
public class CustomersController {

    @GetMapping("/offers/history")
    public String offersHistory() {
        return "Greetings from Customers offers/history!";
    }

    @GetMapping("/offers/current")
    public String offersCurrent() {
        return "Greetings from Customers offers/current!";
    }

    @PostMapping("/offers")
    public String offers() {
        return "Greetings from Customers offers!";
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

    @GetMapping("/receipt/{offerId}")
    public String downloadReceiptForOffer() {
        return "Greetings from Customers downloadReceiptForOffer!";
    }
}