package ru.geekbrains.videoservice.controllers;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;
import ru.geekbrains.videoservice.services.StreamingService;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@WebFluxTest(controllers = StreamingController.class)
public class StreamingControllerTest {

    @Autowired
    private WebTestClient webClient;

    @MockBean
    private StreamingService streamingService;

    @Test
    public void testGetVideo() {
        //arrange
        String inputString = "Hello World!";
        byte[] byteArray = inputString.getBytes();
        InputStream inputStream = new ByteArrayInputStream(byteArray);
        InputStreamResource inputStreamResource = new InputStreamResource(inputStream);
        Mono<Resource> resourceMono = Mono.just(inputStreamResource);

        when(streamingService.getVideo(any())).thenReturn(resourceMono);

        //act and assert
        webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/video")
                        .queryParam("id", "1")
                        .build())
                .exchange()
                .expectStatus().isOk()
                .expectBody(String.class)
                .isEqualTo(inputString);
    }
}
