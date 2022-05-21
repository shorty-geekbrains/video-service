package ru.geekbrains.videoservice.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.geekbrains.videoservice.entities.Video;
import ru.geekbrains.videoservice.entities.VideoRating;
import ru.geekbrains.videoservice.exception.VideoNotFoundException;
import ru.geekbrains.videoservice.exception.VideoRatingNotFoundException;
import ru.geekbrains.videoservice.repository.VideoRatingRepository;
import ru.geekbrains.videoservice.repository.VideoRepository;
import java.util.HashSet;
import java.util.Set;
import static java.util.Objects.isNull;


@Service
@RequiredArgsConstructor
public class VideoRatingService {

    private final VideoRatingRepository videoRatingRepository;
    private final VideoRepository videoRepository;
    private final Set<Long> usersEmoji = new HashSet<>();


    public VideoRating createEmoji(String link, Long numberEmoji) {
        Video video = videoRepository.findByLink(link);
        if (isNull(video)) {
            throw new VideoNotFoundException(String.format("No video found by provided link=%s", link));
        }
        VideoRating videoRating = videoRatingRepository.
                findById(video.getRatingId()).orElseThrow(()
                        -> new VideoRatingNotFoundException("VideoRating cannot be found"));
        if (numberEmoji >= 0) {

            createLike(video, videoRating);
        } else {
            createDislike(video, videoRating);
        }

        return videoRatingRepository.save(videoRating);

    }
    private void createLike(Video video, VideoRating videoRating) {
        if (!usersEmoji.contains(video.getClientId())) {
            videoRating.setLikeCounter(videoRating.getLikeCounter() + 1);
            usersEmoji.add(video.getClientId());
        } else {
            videoRating.setLikeCounter(videoRating.getLikeCounter() - 1);
            usersEmoji.remove(video.getClientId());
        }
    }

    private void createDislike(Video video, VideoRating videoRating) {
        if (!usersEmoji.contains(video.getClientId())) {
            videoRating.setDislikeCounter(videoRating.getDislikeCounter() + 1);
            usersEmoji.add(video.getClientId());
        } else {
            videoRating.setDislikeCounter(videoRating.getDislikeCounter() - 1);
            usersEmoji.remove(video.getClientId());
        }
    }

}


