package com.desafio.um.domain.services;

import com.desafio.um.presentation.services.ICustomerFeedbackService;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import com.desafio.um.domain.models.CustomerFeedback;
import com.desafio.um.infrastructure.enums.CustomFeedbackStatus;

@Service
public class CustomerFeedbackService implements ICustomerFeedbackService {

    private LinkedList<CustomerFeedback> listaSugestao = new LinkedList<>();
    private LinkedList<CustomerFeedback> listaElogio = new LinkedList<>();
    private LinkedList<CustomerFeedback> listaCritica = new LinkedList<>();

    public void addListaSugestao(CustomerFeedback item) {
        listaSugestao.addLast(item);
    }
    public void addListaElogio(CustomerFeedback item) {
        listaElogio.addLast(item);
    }
    public void addListaCritica(CustomerFeedback item) {
        listaCritica.addLast(item);
    }

    public CustomerFeedback removeListaSugestao() {
        if (isEmptyListaSugestao()) {
            throw new IllegalStateException("Queue is empty");
        }
        //alterar o status antes de remover
        CustomerFeedback itemRemovido = listaSugestao.removeFirst();
        itemRemovido.setFeedbackStatus(CustomFeedbackStatus.FINALIZADO);
        return itemRemovido;
    }

    public CustomerFeedback removeListaElogio() {
        if (isEmptyListaElogio()) {
            throw new IllegalStateException("Queue is empty");
        }
        CustomerFeedback itemRemovido = listaElogio.removeFirst();
        itemRemovido.setFeedbackStatus(CustomFeedbackStatus.FINALIZADO);
        return itemRemovido;
    }

    public CustomerFeedback removeListaCritica() {
        if (isEmptyListaCritica()) {
            throw new IllegalStateException("Queue is empty");
        }
        CustomerFeedback itemRemovido = listaCritica.removeFirst();
        itemRemovido.setFeedbackStatus(CustomFeedbackStatus.FINALIZADO);
        return itemRemovido;
    }

    public boolean isEmptyListaSugestao() {
        return listaSugestao.isEmpty();
    }

    public boolean isEmptyListaElogio() {
        return listaElogio.isEmpty();
    }

    public boolean isEmptyListaCritica() {
        return listaCritica.isEmpty();
    }

    public int sizeListaSugestao() {
        return listaSugestao.size();
    }

    public int sizeListaElogio() {
        return listaElogio.size();
    }

    public int sizeListaCritica() {
        return listaCritica.size();
    }
}
