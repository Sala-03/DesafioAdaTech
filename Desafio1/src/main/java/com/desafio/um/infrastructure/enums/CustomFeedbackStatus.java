package com.desafio.um.infrastructure.enums;

public enum CustomFeedbackStatus {
    RECEBIDO("recebido"),
    EMPROCESSAMENTO("emprocessamento"),
    FINALIZADO("finalizado");

    private String description;
    CustomFeedbackStatus(String description) {
        this.description = description;
    }
}