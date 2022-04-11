package ru.geekbrains.videoservice.services;

import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import ru.geekbrains.videoservice.entities.Video;
import ru.geekbrains.videoservice.exceptions.VideoNotFoundException;
import ru.geekbrains.videoservice.repositories.VideoRepository;

import static java.util.Objects.isNull;

@Service
@RequiredArgsConstructor
public class StreamingService {
    private final VideoRepository videoRepository;
    private final ResourceLoader resourceLoader;

    public Mono<Resource> getVideo(Long id) {
        String path = videoRepository.findById(id)
                .map(Video::getLink)
                .orElse(null);

        if (isNull(path)) {
            throw new VideoNotFoundException(String.format("No video found with id=%s", id));
        }

        return Mono.fromSupplier(() -> resourceLoader.getResource(path));
    }
}
