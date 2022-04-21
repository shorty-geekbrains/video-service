package ru.geekbrains.videoservice.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.geekbrains.videoservice.entities.VideoRating;
import ru.geekbrains.videoservice.exception.VideoRatingNotFoundException;
import ru.geekbrains.videoservice.repository.VideoRatingRepository;

import java.util.Optional;


@Service
public class VideoRatingService {

    private final VideoRatingRepository videoRatingRepository;

    @Autowired
    public VideoRatingService(VideoRatingRepository videoRatingRepository) {
        this.videoRatingRepository = videoRatingRepository;
    }


    public VideoRating createLike(Long ratingId, String username) {
        VideoRating videoRating = videoRatingRepository.findById(ratingId)
                .orElseThrow(() -> new VideoRatingNotFoundException("VideoRating cannot be found"));

        Optional<String> userLiked = videoRating.getLikeUsers()
                .stream()
                .filter(u -> u.equals(username)).findAny();
        if (userLiked.isPresent()) {
            videoRating.setLikeCounter(videoRating.getLikeCounter() - 1);
            videoRating.getLikeUsers().remove(username);
        } else {
            videoRating.setLikeCounter(videoRating.getLikeCounter() + 1);
            videoRating.getLikeUsers().add(username);
        }

        return videoRatingRepository.save(videoRating);
    }

    public VideoRating createDislike(Long ratingId, String username) {
        VideoRating videoRating = videoRatingRepository.findById(ratingId)
                .orElseThrow(() -> new VideoRatingNotFoundException("VideoRating cannot be found"));

        Optional<String> userDisliked = videoRating.getDislikeUsers()
                .stream()
                .filter(u -> u.equals(username)).findAny();
        if (userDisliked.isPresent()) {
            videoRating.setDislikeCounter(videoRating.getDislikeCounter() - 1);
            videoRating.getDislikeUsers().remove(username);
        } else {
            videoRating.setDislikeCounter(videoRating.getDislikeCounter() + 1);
            videoRating.getDislikeUsers().add(username);
        }
        return videoRatingRepository.save(videoRating);
    }


}
