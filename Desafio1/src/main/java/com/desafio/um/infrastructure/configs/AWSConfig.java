package com.desafio.um.infrastructure.configs;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.sns.SnsClient;
import software.amazon.awssdk.services.sqs.SqsClient;


@Configuration
public class AWSConfig {

    @Value("${aws.access.key}")
    private String ACCESS_KEY;

    @Value("${aws.secret.key}")
    private String SECRET_KEY;

    @Value("${aws.region}")
    private String AWS_REGION;

    @Bean
    public SnsClient snsClient() {
        return SnsClient.builder()
                .region(Region.of(this.AWS_REGION))
                .credentialsProvider(
                        StaticCredentialsProvider.create(
                                AwsBasicCredentials.create(this.ACCESS_KEY, this.SECRET_KEY)
                        )
                )
                .build();
    }

    @Bean
    public SqsClient sqsClient() {
        return SqsClient.builder()
                .region(Region.of(this.AWS_REGION))
                .credentialsProvider(
                        StaticCredentialsProvider.create(
                                AwsBasicCredentials.create(this.ACCESS_KEY, this.SECRET_KEY)
                        )
                )
                .build();
    }

}
