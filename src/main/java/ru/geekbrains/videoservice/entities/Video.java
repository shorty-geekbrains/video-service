package ru.geekbrains.videoservice.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name = "videos")
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Video {

    @Id
    private Long id;

    private String name;
    private String link;

    @CreationTimestamp
    private LocalDateTime dateUpload;

    private Long ratingId;

    private Long userId;

    private Boolean isAvailable;

    private Long commentId;

    private String description;
}
