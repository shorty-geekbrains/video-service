package ru.geekbrains.videoservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.geekbrains.videoservice.entities.Video;

@Repository
public interface VideoRepository extends JpaRepository<Video, Long> {
}
