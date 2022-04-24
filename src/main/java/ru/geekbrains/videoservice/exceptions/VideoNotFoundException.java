package ru.geekbrains.videoservice.exceptions;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class VideoNotFoundException extends RuntimeException {
    public VideoNotFoundException(String message) {
        super(message);
    }
}
