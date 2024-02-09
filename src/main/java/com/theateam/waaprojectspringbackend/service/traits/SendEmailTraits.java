package com.theateam.waaprojectspringbackend.service.traits;

import com.theateam.waaprojectspringbackend.entity.Offer;
import com.theateam.waaprojectspringbackend.entity.Property;
import com.theateam.waaprojectspringbackend.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
public class SendEmailTraits {
    private final EmailService emailService;

    @Autowired
    public SendEmailTraits(EmailService emailService) {
        this.emailService = emailService;
    }

    @Async
    public void pendingProperty(Property property, Offer offer) {
        String subject = "Your property \"%s\" is now pending!";
        String body = String.format("Congratulations! An offer of $%s has been accepted by user on your property \"%s\". Please check your account for more details.",
                offer.getPrice(), property.getName());

        emailService.sendEmail(offer.getCustomer().getEmail(), subject, body);
    }

    @Async
    public void contingentProperty(Property property, Offer offer) {
        String subject = "Your property \"%s\" is now contingent!";
        String body = String.format("Good news! An offer of $%s has been made on your property \"%s\" and is now contingent. Please check your account for more details.",
                offer.getPrice(), property.getName());

        emailService.sendEmail(property.getOwner().getEmail(), subject, body);
    }
}
