package com.idrive.driveronboardingservice.config;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import org.socialsignin.spring.data.dynamodb.repository.config.EnableDynamoDBRepositories;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.beans.BeanProperty;

@Configuration
@EnableDynamoDBRepositories(basePackages = "com.idrive.driveronboardingservice.repository")
public class DynamoDBConfig {
    @Bean
    public AmazonDynamoDB amazonDynamoDB(AWSCredentials AWSCredentials,
                                         @Value("${aws.dynamoDBUrl}") String dynamoDBURl) {

        AmazonDynamoDBClientBuilder builder = AmazonDynamoDBClientBuilder.standard()
                .withEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration(dynamoDBURl, "eu-central-1"))
                .withCredentials(new AWSStaticCredentialsProvider(AWSCredentials));
        AmazonDynamoDB client = builder.build();

        return client;
    }

    @Bean
    public AWSCredentials awsCredentials(@Value("${aws.accessKey}")
                                         String accesskey,
                                         @Value("${aws.secretKey}")
                                         String secretkey) {
        return new BasicAWSCredentials(accesskey, secretkey);
    }

}
