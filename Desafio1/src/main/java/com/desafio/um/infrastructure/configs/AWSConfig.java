package com.desafio.um.infrastructure.configs;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.auth.DefaultAWSCredentialsProviderChain;
import com.amazonaws.services.sns.AmazonSNS;
import com.amazonaws.services.sns.AmazonSNSClient;
import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sns.AmazonSNSClientBuilder;
import com.amazonaws.services.sqs.AmazonSQSClient;
import com.amazonaws.services.sqs.AmazonSQSClientBuilder;
import com.amazonaws.services.sns.model.Topic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;



@Configuration
public class AWSConfig {

    @Value("${aws.access.key}")
    private String ACCESS_KEY;

    @Value("${aws.secret.key}")
    private String SECRET_KEY;

    @Value("${aws.region}")
    private String awsRegion;

    @Bean
    public AmazonSNS snsClient() {
        return AmazonSNSClient.builder()
                .withRegion(awsRegion)
                .withCredentials(
                                new AWSStaticCredentialsProvider(
                                        new BasicAWSCredentials(this.ACCESS_KEY, this.SECRET_KEY)
                                )
                )
                .build();
    }

    @Bean
    public AmazonSQS sqsClient() {
        return AmazonSQSClient.builder()
                .withRegion(awsRegion)
                .withCredentials(
                        new AWSStaticCredentialsProvider(
                                new BasicAWSCredentials(this.ACCESS_KEY, this.SECRET_KEY)
                        )
                )
                .build();
    }

}
