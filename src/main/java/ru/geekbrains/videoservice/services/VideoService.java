package ru.geekbrains.videoservice.services;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.geekbrains.videoservice.dtos.LinkDto;
import ru.geekbrains.videoservice.repositories.VideoRepository;

@Service
@RequiredArgsConstructor
public class VideoService {

    private final VideoRepository videoRepository;

    public Page<LinkDto> getPageOfLinks(Pageable pageable) {
        return videoRepository.findAll(pageable)
                .map(LinkDto::fromVideoEntity);
    }
}
