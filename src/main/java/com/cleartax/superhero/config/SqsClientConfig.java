package com.cleartax.superhero.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.AwsSessionCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.sqs.SqsClient;

import java.net.URI;

@Configuration
public class SqsClientConfig {

    private final SqsConfig sqsConfig;

    public SqsClientConfig(SqsConfig sqsConfig) {
        this.sqsConfig = sqsConfig;
    }

    @Bean
    public SqsClient sqsClient() {
     //   StaticCredentialsProvider credentialsProvider;
//        if (sqsConfig.getSessionToken() != null && !sqsConfig.getSessionToken().isEmpty()) {
//            credentialsProvider = StaticCredentialsProvider.create(
//                    AwsSessionCredentials.create(
//                            sqsConfig.getAccessKey(),
//                            sqsConfig.getSecretKey(),
//                            sqsConfig.getSessionToken()
//                    )
//            );
//        } else {
//            credentialsProvider = StaticCredentialsProvider.create(
//                    AwsBasicCredentials.create(
//                            sqsConfig.getAccessKey(),
//                            sqsConfig.getSecretKey()
//                    )
//            );
//        }

        return SqsClient.builder()
                .endpointOverride(URI.create(sqsConfig.getQueueUrl()))
                .region(Region.of(sqsConfig.getRegion()))
                .credentialsProvider(StaticCredentialsProvider.create(
                        AwsBasicCredentials.create(sqsConfig.getAccessKey(),
                                sqsConfig.getSecretKey())
                ))
                .build();
    }
}