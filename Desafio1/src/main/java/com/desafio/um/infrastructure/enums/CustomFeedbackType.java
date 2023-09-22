package com.desafio.um.infrastructure.enums;
public enum CustomFeedbackType {
    SUGESTOES("Sugestão", "Sugestao"),
    ELOGIOS("Elogio", "Elogio"),
    CRITICAS("Crítica", "Critica");

    private final String description;

    private final String topicArn;

    CustomFeedbackType(String description, String topicArn) {
        this.description = description;
        this.topicArn = topicArn;
    }

    public String getName() {
        return this.name();
    }

    public String getDescription() {
        return this.description;
    }

    public String getTopicArn() {
        return this.topicArn;
    }
}
