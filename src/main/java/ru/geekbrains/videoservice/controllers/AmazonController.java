package ru.geekbrains.videoservice.controllers;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import ru.geekbrains.videoservice.services.AmazonService;

import java.util.List;

/**
 * @author Nick Musinov e-mail:n.musinov@gmail.com
 * 03.05.2022
 */
@AllArgsConstructor
public class AmazonController {
    private final AmazonService amazonService;

    @GetMapping("/video/amazon")
    public List<String> list() {
       return amazonService.getAllFiles();
    }
}
