package ru.geekbrains.videoservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class VideoRatingNotFoundException extends RuntimeException {
    public VideoRatingNotFoundException(String msg) {
        super(msg);
    }
}
