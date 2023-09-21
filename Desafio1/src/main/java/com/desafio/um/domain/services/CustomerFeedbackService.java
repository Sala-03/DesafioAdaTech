package com.desafio.um.domain.services;

import com.desafio.um.infrastructure.enums.CustomFeedbackType;
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

    public void addLista(CustomerFeedback item) {

        if (item.getFeeedbackType().equals(CustomFeedbackType.SUGESTOES)) {
            item.setFeedbackStatus(CustomFeedbackStatus.RECEBIDO);
            listaSugestao.addLast(item);
        }
        if (item.getFeeedbackType().equals(CustomFeedbackType.ELOGIOS)) {
            item.setFeedbackStatus(CustomFeedbackStatus.RECEBIDO);
            listaElogio.addLast(item);
        }
        if (item.getFeeedbackType().equals(CustomFeedbackType.CRITICAS)) {
            item.setFeedbackStatus(CustomFeedbackStatus.RECEBIDO);
            listaCritica.addLast(item);
        }
    }

    public CustomerFeedback removeLista(CustomFeedbackType type) {
        if (type.equals(CustomFeedbackType.SUGESTOES)) {
            if (isEmptyLista(type)) {
                throw new IllegalStateException("Queue is empty");
            }
            //alterar o status antes de remover
            CustomerFeedback itemRemovido = listaSugestao.removeFirst();
            itemRemovido.setFeedbackStatus(CustomFeedbackStatus.FINALIZADO);
            return itemRemovido;
        } else {
            if (type.equals(CustomFeedbackType.ELOGIOS)) {
                if (isEmptyLista(type)) {
                    throw new IllegalStateException("Queue is empty");
                }
                CustomerFeedback itemRemovido = listaElogio.removeFirst();
                itemRemovido.setFeedbackStatus(CustomFeedbackStatus.FINALIZADO);
                return itemRemovido;
            } else {
                if (isEmptyLista(type)) {
                    throw new IllegalStateException("Queue is empty");
                }
                CustomerFeedback itemRemovido = listaCritica.removeFirst();
                itemRemovido.setFeedbackStatus(CustomFeedbackStatus.FINALIZADO);
                return itemRemovido;
            }
        }
    }
/*
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
    }*/

    public boolean isEmptyLista(CustomFeedbackType type) {
        if (type.equals(CustomFeedbackType.SUGESTOES)) {
            return listaSugestao.isEmpty();
        } else {
            if (type.equals(CustomFeedbackType.ELOGIOS)) {
                return listaElogio.isEmpty();
            } else {
                return listaCritica.isEmpty();
            }
        }
    }

    /*
        public boolean isEmptyListaElogio() {
            return listaElogio.isEmpty();
        }

        public boolean isEmptyListaCritica() {
            return listaCritica.isEmpty();
        }
    */
    public int sizeLista(CustomFeedbackType type) {
        if (type.equals(CustomFeedbackType.SUGESTOES)) {
            return listaSugestao.size();
        } else {
            if (type.equals(CustomFeedbackType.ELOGIOS)) {
                return listaElogio.size();
            } else {
                return listaCritica.size();
            }
        }
    }

    /*
        public int sizeListaElogio() {
            return listaElogio.size();
        }

        public int sizeListaCritica() {
            return listaCritica.size();
        }
    */
    public LinkedList<CustomerFeedback> exibirListaElogios(CustomFeedbackType type) {
        if (type.equals(CustomFeedbackType.ELOGIOS)) {
            return listaElogio;
        } else {
            if (type.equals(CustomFeedbackType.SUGESTOES)) {
                return listaSugestao;
            } else {
                return listaCritica;
            }
        }
    }
/*
    public LinkedList<CustomerFeedback> exibirListaCritica(){
        return listaCritica;
    }

    public LinkedList<CustomerFeedback> exibirListaSugestao(){
        return listaSugestao;
    }

*/
}