package ru.geekbrains.videoservice.entities;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import javax.persistence.*;
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