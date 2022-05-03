package ru.geekbrains.videoservice.services;

import com.amazonaws.services.s3.model.S3ObjectSummary;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.geekbrains.videoservice.Config.AmazonConfig;
import ru.geekbrains.videoservice.constants.Const;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Nick Musinov e-mail:n.musinov@gmail.com
 * 30.04.2022
 */
@Service
@AllArgsConstructor
public class AmazonService {

    private final AmazonConfig amazonConfig;

    public List<String> getAllFiles() {
        List<String> filesNames = new ArrayList();
        List<S3ObjectSummary> files = amazonConfig.getS3().listObjects(Const.BUCKET).getObjectSummaries();
        for (S3ObjectSummary list : files) {
            filesNames.add("net/" + list.getKey());
        }
        return filesNames;
    }
}
