package com.theateam.waaprojectspringbackend.service.impl;

import com.theateam.waaprojectspringbackend.entity.*;
import com.theateam.waaprojectspringbackend.entity.dto.request.CreateOfferDto;
import com.theateam.waaprojectspringbackend.exception.CustomException;
import com.theateam.waaprojectspringbackend.repository.OfferRepo;
import com.theateam.waaprojectspringbackend.repository.PropertyRepo;
import com.theateam.waaprojectspringbackend.repository.UserRepo;
import com.theateam.waaprojectspringbackend.service.OfferService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class OfferServiceImpl {

    private final OfferRepo offerRepo;

    public List<Offer> getAllOfferByCustomerId(Long customerId){
        return offerRepo.findAllByCustomer_Id(customerId);
    }

    @Override
    public List<Offer> getOfferByStatusAndCustomer(Long customerId, OfferStatus status) {
        User user = userRepo.findById(customerId).orElseThrow(() -> new CustomException("User not found"));
        return offerRepo.findAllByCustomerAndStatus(user, status);
    }

}
