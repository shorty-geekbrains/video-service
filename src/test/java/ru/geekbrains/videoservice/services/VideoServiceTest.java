package ru.geekbrains.videoservice.services;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import ru.geekbrains.videoservice.dtos.LinkDto;
import ru.geekbrains.videoservice.entities.Video;
import ru.geekbrains.videoservice.repositories.VideoRepository;

import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.internal.verification.VerificationModeFactory.times;

@ExtendWith(MockitoExtension.class)
public class VideoServiceTest {

    @Mock
    private VideoRepository videoRepository;

    @InjectMocks
    private VideoService videoService;

    @Test
    public void testGetPageOfLinks() {
        //arrange
        String link = "test link";
        Pageable pageable = PageRequest.of(0, 1);
        List<Video> videos = Collections.singletonList(Video.builder().link(link).build());
        Page<Video> videoPage = new PageImpl<>(videos);

        when(videoRepository.findAll(pageable)).thenReturn(videoPage);

        //act
        Page<LinkDto> pageResult = videoService.getPageOfLinks(pageable);
        List<LinkDto> content = pageResult.getContent();

        //assert
        assertThat(content).hasSize(1);
        assertThat(content.get(0).getLink()).isEqualTo(link);
        verify(videoRepository, times(1)).findAll(any(Pageable.class));
    }
}
