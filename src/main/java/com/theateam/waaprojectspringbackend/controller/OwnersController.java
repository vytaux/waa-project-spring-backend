package com.theateam.waaprojectspringbackend.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/owners")
@CrossOrigin
public class OwnersController {

    @PostMapping("/properties")
    public String properties() {
        return "Greetings from Owners properties!";
    }

    @GetMapping("/properties")
    public String getAllProperties() {
        return "Greetings from Owners getAllPoperties!";
    }

    @PutMapping("/properties/{propertyId}")
    public String updateProperty() {
        return "Greetings from Owners updateProperty!";
    }

    @DeleteMapping("/properties/{propertyId}")
    public String deleteProperty() {
        return "Greetings from Owners deleteProperty!";
    }

    @GetMapping("/offers")
    public String offers() {
        return "Greetings from Owners offers!";
    }

    @PutMapping("/offers/:offerId/reject")
    public String rejectOffer() {
        return "Greetings from Owners rejectOffer!";
    }

    @PutMapping("/offers/:offerId/accept")
    public String acceptOffer() {
        return "Greetings from Owners acceptOffer!";
    }

    @PutMapping("/messages")
    public String messages() {
        return "Greetings from Owners messages!";
    }
}
