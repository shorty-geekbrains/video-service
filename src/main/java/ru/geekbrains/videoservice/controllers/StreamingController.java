package ru.geekbrains.videoservice.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;
import ru.geekbrains.videoservice.services.StreamingService;

@RestController
@RequiredArgsConstructor
public class StreamingController {

    private final StreamingService service;

    @GetMapping(value = "video/{title}", produces = "video/mp4")
    public Mono<Resource> getVideo(@PathVariable String title, @RequestHeader("Range") String range) {
        System.out.println(range);
        return service.getVideo(title);
    }

}
