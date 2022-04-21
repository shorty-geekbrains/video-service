package ru.geekbrains.videoservice.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import ru.geekbrains.videoservice.entities.VideoRating;
import ru.geekbrains.videoservice.services.VideoRatingService;

@RestController
public class VideoRatingController {
    private final VideoRatingService videoRatingService;

    public VideoRatingController(VideoRatingService videoRatingService) {
        this.videoRatingService = videoRatingService;
    }

    @GetMapping("/{ratingId}/{username}/like")
    public VideoRating like(@PathVariable("ratingId") String raringId,
                            @PathVariable("username")String username){

        return videoRatingService.createLike(Long.valueOf(raringId),username);
    }

    @GetMapping("/{ratingId}/{username}/dislike")
    public VideoRating dislike(@PathVariable("ratingId") String raringId,
                            @PathVariable("username")String username){

        return videoRatingService.createDislike(Long.valueOf(raringId),username);
    }
}
