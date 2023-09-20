package com.desafio.um.presentation.controllers;

import com.desafio.um.presentation.services.ICustomerFeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.springbootstarter.service.QueueService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/queue")
public class CustomerFeedbackController {

    @Autowired
    private ICustomerFeedbackService customerFeedbackService;

    @PostMapping("/enqueue")
    public void enqueue(@RequestBody String item) {
        queueService.enqueue(item);
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




