package com.desafio.um.presentation.controllers;

import com.desafio.um.domain.models.CustomerFeedback;
import com.desafio.um.infrastructure.enums.CustomFeedbackType;
import com.desafio.um.presentation.dtos.FeedbackDto;
import com.desafio.um.presentation.services.ICustomerFeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping
public class CustomerFeedbackController {

    @Autowired
    private ICustomerFeedbackService customerFeedbackService;

    @PostMapping("/enqueue")
    @ResponseStatus(HttpStatus.CREATED)
    public void enqueue(@RequestBody FeedbackDto feedbackDto) {
        this.customerFeedbackService.addLista(new CustomerFeedback());
    }

    @PostMapping("/{type}")
    @ResponseStatus(HttpStatus.OK)
    public CustomerFeedback dequeue(@PathVariable(name = "type") CustomFeedbackType type) {
        return this.customerFeedbackService.removeLista(type);
    }

    @GetMapping("/{type}")
    @ResponseStatus(HttpStatus.OK)
    public List<CustomerFeedback> getAllByType(@PathVariable(name = "type") CustomFeedbackType type) {
        return this.customerFeedbackService.exibirListaElogios(type);
    }

    @GetMapping("/size/{type}")
    @ResponseStatus(HttpStatus.OK)
    public Integer getListSizeByType(@PathVariable(name = "type") CustomFeedbackType type) {
        return this.customerFeedbackService.sizeLista(type);
    }

}




