package com.desafio.um.infrastructure.exceptions.handler;

import com.desafio.um.infrastructure.exceptions.EmptyQueueException;
import com.desafio.um.infrastructure.exceptions.MalformattedMessageException;
import com.desafio.um.infrastructure.exceptions.NotFoundException;
import com.desafio.um.infrastructure.exceptions.WriteValueAsStringException;
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
    public ResponseEntity<ExceptionDetails> handleEmptyQueueException(EmptyQueueException exception){
        return new ResponseEntity<>(
                ExceptionDetails.builder()
                        .title("Fila vazia")
                        .status(HttpStatus.NOT_FOUND.value())
                        .details(exception.getMessage())
                        .developerMessage(EmptyQueueException.EXCEPTION_DEVELOPER_MESSAGE)
                        .className(Arrays.stream(exception.getStackTrace()).findFirst().get().getClassName())
                        .timestamp(LocalDateTime.now())
                        .build(), HttpStatus.FORBIDDEN
        );
    }

    @ExceptionHandler
    public ResponseEntity<ExceptionDetails> handleMalformattedException(MalformattedMessageException exception){
        return new ResponseEntity<>(
                ExceptionDetails.builder()
                        .title("Objeto mal formatado")
                        .status(HttpStatus.NOT_FOUND.value())
                        .details(exception.getMessage())
                        .developerMessage(MalformattedMessageException.EXCEPTION_DEVELOPER_MESSAGE)
                        .className(Arrays.stream(exception.getStackTrace()).findFirst().get().getClassName())
                        .timestamp(LocalDateTime.now())
                        .build(), HttpStatus.FORBIDDEN
        );
    }

    @ExceptionHandler
    public ResponseEntity<ExceptionDetails> handleNotFoundException(NotFoundException exception){
        return new ResponseEntity<>(
                ExceptionDetails.builder()
                        .title("Não encontrado")
                        .status(HttpStatus.NOT_FOUND.value())
                        .details(exception.getMessage())
                        .developerMessage(NotFoundException.EXCEPTION_DEVELOPER_MESSAGE)
                        .className(Arrays.stream(exception.getStackTrace()).findFirst().get().getClassName())
                        .timestamp(LocalDateTime.now())
                        .build(), HttpStatus.FORBIDDEN
        );
    }

    @ExceptionHandler
    public ResponseEntity<ExceptionDetails> handleWriteValueAsStringException(WriteValueAsStringException exception){
        return new ResponseEntity<>(
                ExceptionDetails.builder()
                        .title("Erro ao converter Objeto para String")
                        .status(HttpStatus.NOT_FOUND.value())
                        .details(exception.getMessage())
                        .developerMessage(WriteValueAsStringException.EXCEPTION_DEVELOPER_MESSAGE)
                        .className(Arrays.stream(exception.getStackTrace()).findFirst().get().getClassName())
                        .timestamp(LocalDateTime.now())
                        .build(), HttpStatus.FORBIDDEN
        );
    }

}
