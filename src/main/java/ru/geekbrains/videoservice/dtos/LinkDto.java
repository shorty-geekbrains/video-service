package ru.geekbrains.videoservice.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.geekbrains.videoservice.entities.Video;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class LinkDto {
    private String link;

    public static LinkDto fromVideoEntity(Video video) {
        return new LinkDto(video.getLink());
    }
}
