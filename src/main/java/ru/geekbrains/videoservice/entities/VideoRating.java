package ru.geekbrains.videoservice.entities;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(schema = "shorty",name = "rating")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class VideoRating {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long likeCounter;

    private Long dislikeCounter;

    private Long viewsCounter;
}
