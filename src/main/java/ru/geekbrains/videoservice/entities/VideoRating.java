package ru.geekbrains.videoservice.entities;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "video_rating")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class VideoRating {

    @Id
    @Column(name = "rating_id")
    private Long id;

    @Column(name = "like_counter")
    private Long likeCounter;

    @Column(name = "dislike_counter")
    private Long dislikeCounter;

    @Column(name = "views_counter")
    private Long viewsCounter;

    @Column
    @ElementCollection(targetClass = String.class)
    private Set<String> likeUsers;

    @Column
    @ElementCollection(targetClass = String.class)
    private Set<String> dislikeUsers;


}
