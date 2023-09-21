package com.desafio.um.domain.models;

import com.desafio.um.infrastructure.enums.CustomFeedbackStatus;
import com.desafio.um.infrastructure.enums.CustomFeedbackType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CustomerFeedback {

    private Integer id;
    private CustomFeedbackType feeedbackType;
    private CustomFeedbackStatus feedbackStatus;
    private String message;

}
