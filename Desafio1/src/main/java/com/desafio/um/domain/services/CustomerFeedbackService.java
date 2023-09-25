package com.desafio.um.domain.services;

import com.desafio.um.infrastructure.configs.AWSConfig;
import com.desafio.um.infrastructure.enums.CustomFeedbackType;
import com.desafio.um.infrastructure.exceptions.MalformattedMessageException;
import com.desafio.um.infrastructure.exceptions.NotFoundException;
import com.desafio.um.infrastructure.exceptions.WriteValueAsStringException;
import com.desafio.um.presentation.dtos.FeedbackDto;
import com.desafio.um.presentation.services.ICustomerFeedbackService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.*;

import com.desafio.um.domain.models.CustomerFeedback;
import com.desafio.um.infrastructure.enums.CustomFeedbackStatus;
import software.amazon.awssdk.services.sns.model.PublishRequest;
import software.amazon.awssdk.services.sqs.model.DeleteMessageRequest;
import software.amazon.awssdk.services.sqs.model.Message;
import software.amazon.awssdk.services.sqs.model.ReceiveMessageRequest;

@Service
public class CustomerFeedbackService implements ICustomerFeedbackService {

    private static final String TOPIC = "TOPIC";

    @Value("${aws.sqs.queue.url}")
    private String SQS_QUEUE_URL;

    @Value("${aws.sns.topic.arn}")
    private String SNS_TOPIC_ARN;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private AWSConfig awsConfig;

    public void addLista(FeedbackDto feedbackDto) {

        CustomerFeedback customerFeedback = new CustomerFeedback(
                feedbackDto.getType(),
                feedbackDto.getMessage()
        );
        try {
            awsConfig.snsClient().publish(
                    PublishRequest.builder()
                            .topicArn(String.format(this.SNS_TOPIC_ARN, feedbackDto.getType().getQueueName()))
                            .message(this.objectMapper.writeValueAsString(customerFeedback))
                            .messageDeduplicationId(customerFeedback.getId().toString())
                            .messageGroupId(TOPIC + "214931549174")
                            .build()
            );
        }
        catch (JsonProcessingException e) {
            throw new WriteValueAsStringException("Erro ao converte Feedback para String");
        }
    }

    public CustomerFeedback removeLista(CustomFeedbackType type) {
        List<Message> messages = this.retrieveMessagesFromSQS(type);
        Message message = Optional.ofNullable(messages.get(0))
                .orElseThrow(() -> new NotFoundException(""));
        CustomerFeedback feedback = this.mapMessageToCustomerFeedback(message);
        feedback.setStatus(CustomFeedbackStatus.FINALIZADO);
        this.awsConfig.sqsClient().deleteMessage(
            DeleteMessageRequest.builder()
                    .queueUrl(String.format(this.SQS_QUEUE_URL, type.getQueueName()))
                    .receiptHandle(message.receiptHandle())
                    .build()
        );
        return feedback;
    }

    public List<CustomerFeedback> exibirLista(CustomFeedbackType type) {
        return this.retrieveMessagesFromSQS(type)
                .stream().map(this::mapMessageToCustomerFeedback)
                .toList();
    }

    public Integer sizeLista(CustomFeedbackType type) {
        return this.retrieveMessagesFromSQS(type).size();
    }

    private List<Message> retrieveMessagesFromSQS(CustomFeedbackType type) {
        return Optional.ofNullable(
                this.awsConfig.sqsClient().receiveMessage(
                        ReceiveMessageRequest.builder()
                                .queueUrl(String.format(this.SQS_QUEUE_URL, type.getQueueName()))
                                .maxNumberOfMessages(1)
                                .build()
                ).messages()
        ).orElseThrow(() -> new NotFoundException("Fila não existe ou vazia"));
    }

    private CustomerFeedback mapMessageToCustomerFeedback(Message message) {
        CustomerFeedback feedback;
        try {
            JsonNode bodyJsonNode = objectMapper.readTree(message.body());
            String messageContent = bodyJsonNode.get("Message").asText();
            feedback = this.objectMapper.readValue(messageContent, CustomerFeedback.class);
        }
        catch (JsonProcessingException e) {
            throw new MalformattedMessageException("Corpo da mensagem não corresponde ao formato de Feedback");
        }
        return feedback;
    }

}