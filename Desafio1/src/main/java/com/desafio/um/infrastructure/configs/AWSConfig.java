package com.desafio.um.infrastructure.configs;

import com.amazonaws.auth.DefaultAWSCredentialsProviderChain;
import com.amazonaws.services.sns.AmazonSNS;
import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sns.AmazonSNSClientBuilder;
import com.amazonaws.services.sqs.AmazonSQSClientBuilder;
import com.amazonaws.services.sns.model.Topic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;



@Configuration


public class AWSConfig {


    @Value("${aws.region}")
    private String awsRegion;

    @Value("${aws.sns.topic.sugestao.arn}")
    private String sugestaoTopic;

    @Value("${aws.sns.topic.elogio.arn}")
    private String elogioTopic;

    @Value("${aws.sns.topic.critica.arn}")
    private String criticaTopic;

    @Bean
    public AmazonSNS snsClient() {
        return AmazonSNSClientBuilder.standard()
                .withRegion(awsRegion)
                .withCredentials(new DefaultAWSCredentialsProviderChain())
                .build();
    }

    @Bean
    public AmazonSQS sqsClient() {
        return AmazonSQSClientBuilder.standard()
                .withRegion(awsRegion)
                .build();
    }

    @Bean(name = "sugestaoTopic")
    public Topic sugestaoTopic() {
        return new Topic().withTopicArn(sugestaoTopic);
    }

    @Bean(name = "elogioTopic")
    public Topic elogioTopic() {
        return new Topic().withTopicArn(elogioTopic);
    }

    @Bean(name = "criticaTopic")
    public Topic criticaTopic() {
        return new Topic().withTopicArn(criticaTopic);
    }
}
