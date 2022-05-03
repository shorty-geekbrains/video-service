package ru.geekbrains.videoservice.services;


import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.S3ObjectSummary;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.geekbrains.videoservice.constants.Const;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Nick Musinov e-mail:n.musinov@gmail.com
 * 30.04.2022
 */
@Service

public class AmazonService {

    AWSCredentials credentials = new BasicAWSCredentials(
            Const.ACCESS_KEY,
            Const.SECRET_KEY
    );


    AmazonS3 s3 = AmazonS3ClientBuilder.standard()
            .withCredentials(new AWSStaticCredentialsProvider(credentials))
            .withEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration(
                    Const.SERVER_ENDPOINT, Const.REGION)
            )
            .build();


    public List<String> getAllFiles() {
        List<String> filesNames = new ArrayList();
        List<S3ObjectSummary> files = s3.listObjects(Const.BUCKET).getObjectSummaries();
        for (S3ObjectSummary list : files) {
            filesNames.add("net/" + list.getKey());
        }
        return filesNames;
    }
}
