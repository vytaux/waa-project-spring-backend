package com.theateam.waaprojectspringbackend.controller;

import com.theateam.waaprojectspringbackend.entity.Offer;
import com.theateam.waaprojectspringbackend.entity.OfferStatus;
import com.theateam.waaprojectspringbackend.entity.User;
import com.theateam.waaprojectspringbackend.entity.dto.request.CreateOfferDto;
import com.theateam.waaprojectspringbackend.entity.dto.request.UpdateOfferDto;
import com.theateam.waaprojectspringbackend.entity.dto.response.OfferResponseDto;
import com.theateam.waaprojectspringbackend.service.OfferService;
import com.theateam.waaprojectspringbackend.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/customers")
@CrossOrigin
public class CustomersController {

    private final OfferService offerService;
    private final UserService userService;

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

    @GetMapping("/saved-properties")
    public ResponseEntity<List<String>> getSavedProperties() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userEmail = authentication.getName();
        Optional<User> user = userService.getUserByEmail(userEmail);

        if(user!=null){
            List<String> savedPropertyIds = user.get().getSavedPropertyIds();
            return ResponseEntity.ok(savedPropertyIds);
        }else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @PostMapping("/saved-properties/{propertyId}")
    public ResponseEntity<String> savedProperties(@PathVariable String propertyId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userEmail = authentication.getName();
        Optional<User> user = userService.getUserByEmail(userEmail);

        if(user.isPresent()){
            List<String> savedPropertyIds = user.get().getSavedPropertyIds();
            if(savedPropertyIds==null){
                savedPropertyIds = new ArrayList<>();
            }
            if(!savedPropertyIds.contains(propertyId)){
                savedPropertyIds.add(propertyId);
                user.get().setSavedPropertyIds(savedPropertyIds);
                userService.saveUser(user);
                return ResponseEntity.ok("Property Id saved successfully");
            }else {
                return ResponseEntity.badRequest().body("Property Id already exist.");
            }
        }
        else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("User not found.");
        }
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
