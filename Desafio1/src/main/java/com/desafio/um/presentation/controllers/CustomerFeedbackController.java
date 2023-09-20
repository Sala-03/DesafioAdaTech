package com.desafio.um.presentation.controllers;

import com.desafio.um.presentation.services.ICustomerFeedbackService;
import org.springframework.beans.factory.annotation.Autowired;

public class CustomerFeedbackController {

    @Autowired
    private ICustomerFeedbackService customerFeedbackService;

}
