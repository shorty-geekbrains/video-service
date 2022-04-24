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
@Table(schema = "shorty", name = "video")
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
    private LocalDateTime uploadDate;

    private Long ratingId;

    private Long clientId;

    private Boolean isAvailable;

    private String description;
}
