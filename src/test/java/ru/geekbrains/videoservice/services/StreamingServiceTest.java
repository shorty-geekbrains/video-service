package ru.geekbrains.videoservice.services;

import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import reactor.core.publisher.Mono;
import ru.geekbrains.videoservice.entities.Video;
import ru.geekbrains.videoservice.repositories.VideoRepository;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;

import static java.util.Objects.nonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.internal.verification.VerificationModeFactory.times;

@ExtendWith(MockitoExtension.class)
public class StreamingServiceTest {

    private final static String TEST_LINK = "test link";

    @Mock
    private VideoRepository videoRepository;

    @Mock
    private ResourceLoader resourceLoader;

    @InjectMocks
    private StreamingService streamingService;

    @SneakyThrows(IOException.class)
    @Test
    public void testGetVideo() {
        //arrange
        Video video = Video.builder().link(TEST_LINK).build();
        byte[] byteArray = TEST_LINK.getBytes();
        InputStream inputStream = new ByteArrayInputStream(byteArray);
        InputStreamResource inputStreamResource = new InputStreamResource(inputStream);

        when(videoRepository.findByLink(any())).thenReturn(video);
        when(resourceLoader.getResource(any())).thenReturn(inputStreamResource);

        //act
        Mono<Resource> mono = streamingService.getVideo(TEST_LINK);

        byte[] bytes = Objects.requireNonNull(mono.block()).getInputStream().readAllBytes();

        String result = new String(nonNull(bytes) ? bytes : new byte[0]);

        //assert
        assertEquals(TEST_LINK, result);
        verify(videoRepository, times(1)).findByLink(any());
    }
}
