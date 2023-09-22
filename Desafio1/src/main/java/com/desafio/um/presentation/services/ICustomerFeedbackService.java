package com.desafio.um.presentation.services;

import com.desafio.um.domain.models.CustomerFeedback;
import com.desafio.um.infrastructure.enums.CustomFeedbackType;

import com.desafio.um.presentation.dtos.FeedbackDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ICustomerFeedbackService {

    void addLista(FeedbackDto feedbackDto);

    CustomerFeedback removeLista(CustomFeedbackType type);

    Integer sizeLista(CustomFeedbackType type);

    List<CustomerFeedback> exibirLista(CustomFeedbackType type);

}
