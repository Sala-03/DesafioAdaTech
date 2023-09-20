package com.desafio.um.infrastructure.enums;
public enum CustomFeedbackType {
    SUGESTOES("sugestoes"),
    ELOGIOS("elogios"),
    CRITICAS("criticas");

    private String description;
    CustomFeedbackType(String description) {
        this.description = description;
    }
}
