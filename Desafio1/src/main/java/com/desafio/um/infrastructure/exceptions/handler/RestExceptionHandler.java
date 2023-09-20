package com.desafio.um.infrastructure.exceptions.handler;

import com.desafio.um.infrastructure.exceptions.NotFoundException;
import com.desafio.um.infrastructure.exceptions.details.ExceptionDetails;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;
import java.util.Arrays;

@ControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<ExceptionDetails> handleNotFoundException(NotFoundException exception){
        return new ResponseEntity<>(
                ExceptionDetails.builder()
                        .title("Não encontrado")
                        .status(HttpStatus.FORBIDDEN.value())
                        .details(exception.getMessage())
                        .developerMessage(NotFoundException.EXCEPTION_DEVELOPER_MESSAGE)
                        .className(Arrays.stream(exception.getStackTrace()).findFirst().get().getClassName())
                        .timestamp(LocalDateTime.now())
                        .build(), HttpStatus.FORBIDDEN
        );
    }

}
