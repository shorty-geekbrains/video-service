package ru.geekbrains.videoservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.geekbrains.videoservice.entities.Video;

public interface VideoRepository extends JpaRepository<Video, Long> {

    Video findByLink(String link);
}