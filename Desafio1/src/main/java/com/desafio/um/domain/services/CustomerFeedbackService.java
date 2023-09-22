package com.desafio.um.domain.services;

import com.amazonaws.services.sns.model.NotFoundException;
import com.amazonaws.services.sns.model.PublishRequest;
import com.amazonaws.services.sqs.model.DeleteMessageRequest;
import com.amazonaws.services.sqs.model.Message;
import com.amazonaws.services.sqs.model.ReceiveMessageRequest;
import com.desafio.um.infrastructure.configs.AWSConfig;
import com.desafio.um.infrastructure.enums.CustomFeedbackType;
import com.desafio.um.presentation.dtos.FeedbackDto;
import com.desafio.um.presentation.services.ICustomerFeedbackService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.*;

import com.desafio.um.domain.models.CustomerFeedback;
import com.desafio.um.infrastructure.enums.CustomFeedbackStatus;

@Service
public class CustomerFeedbackService implements ICustomerFeedbackService {

    @Value("${aws.sns.topic.arn}")
    private String SNS_TOPIC_ARN;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private AWSConfig snsConfig;

    public void addLista(FeedbackDto feedbackDto) {

        CustomerFeedback customerFeedback = new CustomerFeedback(
                feedbackDto.getType(),
                feedbackDto.getMessage()
        );

        try {
            snsConfig.snsClient().publish(
                    new PublishRequest()
                            .withTopicArn(String.format(this.SNS_TOPIC_ARN, feedbackDto.getType().getTopicArn()))
                            .withMessage(this.objectMapper.writeValueAsString(customerFeedback))
            );
        }
        catch (JsonProcessingException e) {

        }
    }

    public CustomerFeedback removeLista(CustomFeedbackType type) {
        List<Message> messages = this.retrieveMessagesFromSQS(type);
        Message message = messages.get(0);
        CustomerFeedback feedback = this.mapMessageToCustomerFeedback(message);
        feedback.setStatus(CustomFeedbackStatus.FINALIZADO);
        this.snsConfig.sqsClient().deleteMessage(
                new DeleteMessageRequest()
                        .withQueueUrl("")
                        .withReceiptHandle(message.getReceiptHandle())
        );
        return feedback;
    }

    public List<CustomerFeedback> exibirLista(CustomFeedbackType type) {
        return this.retrieveMessagesFromSQS(type)
                .stream().map(this::mapMessageToCustomerFeedback)
                .toList();
    }

    public Integer sizeLista(CustomFeedbackType type) {
        return this.retrieveMessagesFromSQS(type)
                .stream().map(this::mapMessageToCustomerFeedback).toList()
                .size();
    }

    private List<Message> retrieveMessagesFromSQS(CustomFeedbackType type) {
        return Optional.ofNullable(
                this.snsConfig.sqsClient().receiveMessage(
                        new ReceiveMessageRequest()
                                .withQueueUrl("")
                                .withMaxNumberOfMessages(1)
                ).getMessages()
        ).orElseThrow(() -> new NotFoundException("Fila vazia"));
    }

    private CustomerFeedback mapMessageToCustomerFeedback(Message message) {
        CustomerFeedback feedback = new CustomerFeedback();
        try {
            feedback = this.objectMapper.readValue(message.getBody(), CustomerFeedback.class);
        }
        catch (JsonProcessingException e) {

        }
        return feedback;
    }

}