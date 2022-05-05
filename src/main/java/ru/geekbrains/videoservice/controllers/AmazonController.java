package ru.geekbrains.videoservice.controllers;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import ru.geekbrains.videoservice.services.AmazonService;

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

    @PostMapping("/video/save")
    public void storeFile(@RequestParam(value = "file") MultipartFile file) {
        new Thread(() -> {
            amazonService.uploadFile(file);
        }).start();
    }

}
