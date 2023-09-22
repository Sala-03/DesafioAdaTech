package com.desafio.um.domain.models;

import com.desafio.um.infrastructure.enums.CustomFeedbackStatus;
import com.desafio.um.infrastructure.enums.CustomFeedbackType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CustomerFeedback {

    private UUID id;
    private CustomFeedbackType type;
    private CustomFeedbackStatus status;
    private String message;

    public CustomerFeedback(CustomFeedbackType type, String message) {
        this.id = UUID.randomUUID();
        this.type = type;
        this.status = CustomFeedbackStatus.RECEBIDO;
        this.message = message;
    }

}
