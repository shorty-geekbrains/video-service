package ru.geekbrains.videoservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.geekbrains.videoservice.entities.VideoRating;


@Repository
public interface VideoRatingRepository extends JpaRepository<VideoRating, Long> {


}
