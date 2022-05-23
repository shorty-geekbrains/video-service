package ru.geekbrains.videoservice.controllers;

import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import ru.geekbrains.videoservice.dtos.LinkDto;
import ru.geekbrains.videoservice.services.VideoService;

import java.util.Collections;
import java.util.List;

import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = VideoController.class)
public class VideoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private VideoService videoService;

    @SneakyThrows
    @Test
    public void testGetPageOfLinks() {
        //arrange
        String link = "link link link";
        List<LinkDto> list = Collections.singletonList(new LinkDto(link));
        Page<LinkDto> page = new PageImpl<>(list);

        when(videoService.getPageOfLinks(any())).thenReturn(page);

        //act and assert
        mockMvc.perform(get("/video/list")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"link\": \"test\"}"))
                .andExpect(jsonPath("$.content", hasSize(1)))
                .andExpect(jsonPath("$.content[0].link").value(link))
                .andExpect(status().isOk());
    }
}
