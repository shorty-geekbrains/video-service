package ru.geekbrains.videoservice.repositories;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import ru.geekbrains.videoservice.constants.Const;

/**
 * @author Nick Musinov e-mail:n.musinov@gmail.com
 * 30.04.2022
 */
public class AmazonRepo {
    private AmazonS3 s3;

    AWSCredentials credentials = new BasicAWSCredentials(
            Const.ACCESS_KEY,
            Const.SECRET_KEY
    );

    public AmazonS3 buildS3() {
        s3 = AmazonS3ClientBuilder.standard()
                .withCredentials(new AWSStaticCredentialsProvider(credentials))
                .withEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration(Const.SERVER_ENDPOINT, Const.REGION))
                .build();
        return s3;
    }


}
