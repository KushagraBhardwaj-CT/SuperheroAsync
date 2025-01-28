package com.cleartax.superhero.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SqsConfig {

    @Value("${aws.sqs.queueUrl}")
    private String queueUrl;

    @Value("${aws.sqs.queueName}")
    private String queueName;

    @Value("${aws.sqs.region}")
    private String region;

    @Value("${aws.sqs.accessKey}")
    private String accessKey;

    @Value("${aws.sqs.secretKey}")
    private String secretKey;

    @Value("${aws.sqs.sessionToken:}")
    private String sessionToken;

    public String getQueueUrl() {
        return queueUrl;
    }

    public String getQueueName() {
        return queueName;
    }

    public String getRegion() {
        return region;
    }

    public String getAccessKey() {
        return accessKey;
    }

    public String getSecretKey() {
        return secretKey;
    }

    public String getSessionToken() {
        return sessionToken;
    }
}