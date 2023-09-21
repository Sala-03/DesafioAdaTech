package com.desafio.um.presentation.controllers;

import com.desafio.um.infrastructure.enums.CustomFeedbackType;
import com.desafio.um.presentation.dtos.FeedbackDto;
import com.desafio.um.presentation.services.ICustomerFeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping
public class CustomerFeedbackController {

    @Autowired
    private ICustomerFeedbackService customerFeedbackService;

    @PostMapping
    public void enqueue(@RequestBody FeedbackDto feedbackDto) {

    }

    @PostMapping("/{type}")
    public void dequeue() {

    }

    @GetMapping("/{type}")
    public void getAll(@PathVariable(name = "type") CustomFeedbackType type) {

    }

    @GetMapping
    public void getAll() {

    }

}




