package com.desafio.um.infrastructure.enums;
public enum CustomFeedbackType {
    SUSGESTOES("sugestoes"),
    ELOGIOS("elogios"),
    CRITICAS("criticas");

    private String description;
    CustomFeedbackType(String description) {
        this.description = description;
    }
}
