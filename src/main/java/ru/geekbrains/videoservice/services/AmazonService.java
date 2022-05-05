package ru.geekbrains.videoservice.services;


import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.S3ObjectSummary;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.geekbrains.videoservice.constants.AmazonConst;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Nick Musinov e-mail:n.musinov@gmail.com
 * 30.04.2022
 */
@Service
public class AmazonService {

    AWSCredentials credentials = new BasicAWSCredentials(
            AmazonConst.ACCESS_KEY,
            AmazonConst.SECRET_KEY
    );


    AmazonS3 s3 = AmazonS3ClientBuilder.standard()
            .withCredentials(new AWSStaticCredentialsProvider(credentials))
            .withEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration(
                    AmazonConst.SERVER_ENDPOINT, AmazonConst.REGION)
            )
            .build();


    public List<String> getAllFiles() {
        List<String> filesNames = new ArrayList();
        List<S3ObjectSummary> files = s3.listObjects(AmazonConst.BUCKET).getObjectSummaries();
        for (S3ObjectSummary list : files) {
            filesNames.add("https://shorty.storage.yandexcloud.net/" + list.getKey());
        }
        return filesNames;
    }


    public void uploadFile(MultipartFile file) {
        File fileObj = convertMultiPartFileToFile(file);
        String fileName = file.getOriginalFilename();
        s3.putObject(new PutObjectRequest(AmazonConst.BUCKET, fileName, fileObj));
        fileObj.delete();
    }

    private File convertMultiPartFileToFile(MultipartFile file) {
        File convertedFile = new File(file.getOriginalFilename());
        try (FileOutputStream fos = new FileOutputStream(convertedFile)) {
            fos.write(file.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return convertedFile;
    }

}
