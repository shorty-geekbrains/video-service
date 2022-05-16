package ru.geekbrains.videoservice.controllers;

import io.github.techgnious.exception.VideoException;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.geekbrains.videoservice.services.AmazonService;

import java.io.IOException;
import java.util.List;

/**
 * @author Nick Musinov e-mail:n.musinov@gmail.com
 * 03.05.2022
 */
@RestController
@AllArgsConstructor
public class AmazonController {
    private final AmazonService amazonService;

    @GetMapping("/video/amazon")
    public List<String> list() {
        return amazonService.getAllFiles();
    }

    @PostMapping(value = "/video/save", consumes = "multipart/form-data")
    public void storeFile(@RequestParam(value = "file") MultipartFile file) {
        new Thread(() -> {
            try {
                amazonService.uploadFile(file);
            } catch (IOException | VideoException e) {
                e.printStackTrace();
            }
        }).start();
    }

}
