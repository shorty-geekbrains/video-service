package ru.geekbrains.videoservice.services;


import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.S3ObjectSummary;
import io.github.techgnious.IVCompressor;
import io.github.techgnious.dto.IVAudioAttributes;
import io.github.techgnious.dto.IVSize;
import io.github.techgnious.dto.IVVideoAttributes;
import io.github.techgnious.dto.VideoFormats;
import io.github.techgnious.exception.VideoException;
import lombok.RequiredArgsConstructor;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;

/**
 * @author Nick Musinov e-mail:n.musinov@gmail.com
 * 30.04.2022
 */
@Service
@RequiredArgsConstructor
public class AmazonService {

    private final AmazonS3 s3;

    @Value("${amazon.bucket}")
    private String bucket;
    @Value("${amazon.storage-url")
    private String storageUrl;

    public List<String> getAllFiles() {
        List<String> filesNames = new ArrayList<>();
        List<S3ObjectSummary> files = s3.listObjects(bucket).getObjectSummaries();
        for (S3ObjectSummary list : files) {
            filesNames.add(storageUrl + list.getKey());
        }
        return filesNames;
    }

    public void uploadFile(MultipartFile file) throws IOException, VideoException {
        File fileObj = convertMultiPartFileToFile(file);
        s3.putObject(new PutObjectRequest(bucket, fileObj.getName(), fileObj));
        fileObj.delete();
    }

    public File convertMultiPartFileToFile(MultipartFile file) throws IOException, VideoException {
        byte[] bytes = file.getBytes();
        File compressedFile = new File(System.currentTimeMillis() + ".mp4");
        System.out.println(file.isEmpty());
        IVCompressor compressor = new IVCompressor();
        byte[] converted = converter(bytes, FilenameUtils.getExtension(file.getOriginalFilename()));
        IVSize customRes = new IVSize();
        customRes.setHeight(720);
        customRes.setWidth(1280);
        IVVideoAttributes videoAttribute = new IVVideoAttributes();
        videoAttribute.setBitRate(3000000);
        videoAttribute.setSize(customRes);
        IVAudioAttributes audioAttribute = new IVAudioAttributes();
        audioAttribute.setBitRate(128000);
        audioAttribute.setChannels(2);
        audioAttribute.setSamplingRate(48000);
        byte[] compressed = compressor.encodeVideoWithAttributes(converted, VideoFormats.MP4, audioAttribute, videoAttribute);
        try (FileOutputStream fos = new FileOutputStream(compressedFile)) {
            fos.write(compressed);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return compressedFile;
    }

    public byte[] converter(byte[] arr, String extension) throws VideoException {
        boolean supported = false;
        IVCompressor compressor = new IVCompressor();
        List<VideoFormats> videoFormats = new ArrayList<>(EnumSet.allOf(VideoFormats.class));
        byte[] bytes = new byte[arr.length];

        for (VideoFormats videoFormat : videoFormats) {
            if (extension.equals(String.valueOf(videoFormat).toLowerCase())) {
                System.out.println("Upload file with extension " + extension);
                supported = true;
            }
        }
        if (supported) {
            bytes = compressor.convertVideoFormat(arr, VideoFormats.valueOf(extension.toUpperCase()), VideoFormats.MP4);
        } else {
            System.err.println("File extension is unsupported");
        }

        return bytes;
    }

}
