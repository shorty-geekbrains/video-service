package ru.geekbrains.videoservice.services;


import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.S3ObjectSummary;
import io.github.techgnious.IVCompressor;
import io.github.techgnious.dto.ResizeResolution;
import io.github.techgnious.dto.VideoFormats;
import io.github.techgnious.exception.VideoException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.geekbrains.videoservice.Const.AmazonConst;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Nick Musinov e-mail:n.musinov@gmail.com
 * 30.04.2022
 */
@Service
public class AmazonService {

//    @Value("${amazon.access-key}")
//    private String accessKey;
//    @Value("${amazon.secret-key}")
//    private String secretKey;
//    @Value("${amazon.server-endpoint}")
//    private String serverEndpoint;
//    @Value("${amazon.region}")
//    private String region;
//    @Value("${amazon.bucket}")
//    private String bucket;
//    @Value("${amazon.storage-url")
//    private String storageUrl;

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
        List<String> filesNames = new ArrayList<>();
        List<S3ObjectSummary> files = s3.listObjects(AmazonConst.BUCKET).getObjectSummaries();
        for (S3ObjectSummary list : files) {
            filesNames.add(AmazonConst.STORAGE_URL + list.getKey());
        }
        return filesNames;
    }

    public void uploadFile(MultipartFile file) throws IOException, VideoException {
        File fileObj = convertMultiPartFileToFile(file);
        s3.putObject(new PutObjectRequest(AmazonConst.BUCKET, fileObj.getName(), fileObj));
        fileObj.delete();
    }

    private File convertMultiPartFileToFile(MultipartFile file) throws IOException, VideoException {
        File compressedFile = new File(String.valueOf(System.currentTimeMillis()) + ".mp4");
        IVCompressor compressor = new IVCompressor();
        File file1 = new File(file.getOriginalFilename());
        try (FileOutputStream fos = new FileOutputStream(file1)) {
            fos.write(file.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
        byte[] compressed = compressor.reduceVideoSize(file1, VideoFormats.MP4, ResizeResolution.R720P);
        try(FileOutputStream fos1 = new FileOutputStream(compressedFile)) {
            fos1.write(compressed);
        }
        return compressedFile;
    }

}
