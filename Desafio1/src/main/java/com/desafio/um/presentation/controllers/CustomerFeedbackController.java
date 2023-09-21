package com.desafio.um.presentation.controllers;

import com.desafio.um.domain.services.CustomerFeedbackService;
import com.desafio.um.presentation.services.ICustomerFeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
public class CustomerFeedbackController {

    @Autowired
    private ICustomerFeedbackService customerFeedbackService;

    @PostMapping("/enqueue")
    public void enqueue(@RequestBody String item) {
        CustomerFeedbackService.(item);
    }

    @GetMapping("/dequeue")
    public String dequeue() {
        return queueService.dequeue();
    }

    @GetMapping("/isEmpty")
    public boolean isEmpty() {
        return queueService.isEmpty();
    }

    @GetMapping("/size")
    public int size() {
        return queueService.size();
    }
}




