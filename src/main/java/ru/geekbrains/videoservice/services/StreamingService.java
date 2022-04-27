package ru.geekbrains.videoservice.services;

import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import ru.geekbrains.videoservice.entities.Video;
import ru.geekbrains.videoservice.exceptions.IncorrectLinkException;
import ru.geekbrains.videoservice.exceptions.VideoNotFoundException;
import ru.geekbrains.videoservice.repositories.VideoRepository;

import static java.util.Objects.isNull;
import static org.apache.commons.lang3.StringUtils.isEmpty;
import static org.apache.commons.lang3.StringUtils.substringAfter;

@Service
@RequiredArgsConstructor
public class StreamingService {
    private static final String PREFIX = "video-service/video/";

    private final VideoRepository videoRepository;
    private final ResourceLoader resourceLoader;

    public Mono<Resource> getVideo(String link) {

        if (isEmpty(link)) {
            throw new IncorrectLinkException(String.format("Incorrect link=%s", link));
        }

        Video video = videoRepository.findByLink(link);

        if (isNull(video)) {
            throw new VideoNotFoundException(String.format("No video found by provided link=%s", link));
        }

        String path = substringAfter(link, PREFIX);

        return Mono.fromSupplier(() -> resourceLoader.getResource(path));
    }
}
