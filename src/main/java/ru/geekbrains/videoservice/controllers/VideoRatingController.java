package ru.geekbrains.videoservice.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.geekbrains.videoservice.entities.VideoRating;
import ru.geekbrains.videoservice.services.VideoRatingService;

@RestController
@RequiredArgsConstructor
public class VideoRatingController {

    private final VideoRatingService videoRatingService;

    @PostMapping("video/{link}/{number}")
    public VideoRating like(@PathVariable("link") String link,
                            @PathVariable("number") Long number){
        return videoRatingService.createEmoji(link,number);
    }
}
