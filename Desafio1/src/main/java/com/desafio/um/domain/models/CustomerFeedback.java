package com.desafio.um.domain.models;
import com.desafio.um.infrastructure.enums.CustomFeedbackStatus;
import com.desafio.um.infrastructure.enums.CustomFeedbackType;
import lombok.Getter;
import lombok.Setter;

import java.rmi.server.UID;

@Getter @Setter

public class CustomerFeedback {
    private UID userID;
    private CustomFeedbackType feeedbackType;
    private CustomFeedbackStatus feedbackStatus;
    private String message;

    public CustomerFeedback(
            UID id,
            CustomFeedbackType type,
            CustomFeedbackStatus status,
            String message
    ) {
        this.userID = id;
        this.feeedbackType = type;
        this.feedbackStatus = status;
        this.message = message;
    }
}
