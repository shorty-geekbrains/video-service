package ru.geekbrains.videoservice.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.geekbrains.videoservice.dtos.LinkDto;
import ru.geekbrains.videoservice.services.VideoService;

@RestController
@RequiredArgsConstructor
public class VideoController {

    private final VideoService videoService;

    @GetMapping("video/list")
    public ResponseEntity<Page<LinkDto>> getPageOfLinks(@PageableDefault(size = 3) Pageable pageable) {
        return ResponseEntity.ok(videoService.getPageOfLinks(pageable));
    }
}
