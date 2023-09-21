package com.desafio.um.presentation.services;

import com.desafio.um.domain.models.CustomerFeedback;
import com.desafio.um.infrastructure.enums.CustomFeedbackType;
import org.springframework.stereotype.Service;

import java.util.LinkedList;


@Service
public interface ICustomerFeedbackService {

    void addLista(CustomerFeedback item);

    CustomerFeedback removeLista(CustomFeedbackType type);

    boolean isEmptyLista(CustomFeedbackType type);

    int sizeLista(CustomFeedbackType type);

    LinkedList<CustomerFeedback> exibirListaElogios(CustomFeedbackType type);
/*
    CustomerFeedback removeListaSugestao();

    CustomerFeedback removeListaElogio();

    CustomerFeedback removeListaCritica();

    boolean isEmptyListaSugestao();

    boolean isEmptyListaElogio();

    boolean isEmptyListaCritica();

    int sizeListaSugestao();

    int sizeListaElogio();

    int sizeListaCritica();

    LinkedList<CustomerFeedback> exibirListaElogios();

    LinkedList<CustomerFeedback> exibirListaCritica();

    LinkedList<CustomerFeedback> exibirListaSugestao();
*/
}
