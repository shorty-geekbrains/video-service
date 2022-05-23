package ru.geekbrains.videoservice.services;

import io.github.techgnious.IVCompressor;
import io.github.techgnious.dto.VideoFormats;
import io.github.techgnious.exception.VideoException;
import org.apache.commons.io.FilenameUtils;
import org.aspectj.util.FileUtil;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.net.URLConnection;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Nick Musinov e-mail:n.musinov@gmail.com
 * 19.05.2022
 */
class AmazonServiceTest {

    @Disabled
    @Test
    void converter() throws IOException, VideoException {
        File avi = new File("src/test/resources/videos/avi.avi");
        File flv = new File("src/test/resources/videos/flv.flv");
        File mkv = new File("src/test/resources/videos/mkv.mkv");
        File mov = new File("src/test/resources/videos/mov.mov");
        File mp4 = new File("src/test/resources/videos/mp4.mp4");
        File wmv = new File("src/test/resources/videos/wmv.wmv");

        List<File> fileList = new ArrayList<>();
        fileList.add(avi);
        fileList.add(flv);
//        fileList.add(mkv);
        fileList.add(mov);
        fileList.add(mp4);
//        fileList.add(wmv);

        IVCompressor compressor = new IVCompressor();
        for (File file : fileList) {
            String inputFileExtension = FilenameUtils.getExtension(file.getName());
            byte[] bytes = FileUtil.readAsByteArray(file);
            byte[] converted = compressor.convertVideoFormat(bytes, VideoFormats.valueOf(inputFileExtension.toUpperCase()), VideoFormats.MP4);
            File convertedFile = new File("converted__" + FilenameUtils.getExtension(file.getName()) + ".mp4");
            FileOutputStream fos = new FileOutputStream(convertedFile);
            fos.write(converted);
            String convertedFileType = Files.probeContentType(convertedFile.toPath());
            assertEquals("video/mp4", convertedFileType);

        }
    }
    @Disabled
    @Test
    void test() throws IOException {
        File avi = new File("src/test/resources/videos/mov.mov");
        byte[] bytes = FileUtil.readAsByteArray(avi);
        File file = new File("src/test/resources/videos/test2.mp4");
        FileOutputStream fos = new FileOutputStream(file);
        fos.write(bytes);
    }
}