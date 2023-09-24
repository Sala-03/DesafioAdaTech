package com.desafio.um.infrastructure.enums;
public enum CustomFeedbackType {

    SUGESTOES("Sugestão", "Sugestao"),
    ELOGIOS("Elogio", "Elogio"),
    CRITICAS("Crítica", "Critica");

    private final String description;

    private final String queueName;

    CustomFeedbackType(String description, String queueName) {
        this.description = description;
        this.queueName = queueName;
    }

    public String getName() {
        return this.name();
    }

    public String getDescription() {
        return this.description;
    }

    public String getQueueName() {
        return this.queueName;
    }
}
