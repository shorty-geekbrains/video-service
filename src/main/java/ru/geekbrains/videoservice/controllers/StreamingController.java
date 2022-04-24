package ru.geekbrains.videoservice.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;
import ru.geekbrains.videoservice.services.StreamingService;

@RestController
@RequiredArgsConstructor
public class StreamingController {

    private final StreamingService service;

    @GetMapping(value = "video", produces = "video/mp4")
    public Mono<Resource> getVideo(@RequestParam Long id) {
        return service.getVideo(id);
    }

}
