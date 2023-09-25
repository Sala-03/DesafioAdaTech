package com.desafio.um.presentation.dtos;

import com.desafio.um.infrastructure.enums.CustomFeedbackType;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FeedbackDto {

    @NotEmpty
    private String message;

    @NotNull
    private CustomFeedbackType type;

}
