package ru.geekbrains.videoservice.config;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AWSClientConfig {

    @Value("${amazon.access-key}")
    private String accessKey;
    @Value("${amazon.secret-key}")
    private String secretKey;
    @Value("${amazon.server-endpoint}")
    private String serverEndpoint;
    @Value("${amazon.region}")
    private String region;

    @Bean
    public BasicAWSCredentials basicAWSCredentials() {
        return new BasicAWSCredentials(accessKey, secretKey);
    }

    @Bean
    public AmazonS3 amazonS3() {
        return AmazonS3ClientBuilder.standard()
                .withCredentials(new AWSStaticCredentialsProvider(basicAWSCredentials()))
                .withEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration(serverEndpoint, region))
                .build();
    }
}
