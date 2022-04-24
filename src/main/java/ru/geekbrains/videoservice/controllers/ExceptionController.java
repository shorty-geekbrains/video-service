package ru.geekbrains.videoservice.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import ru.geekbrains.videoservice.exceptions.VideoNotFoundException;

@ControllerAdvice
public class ExceptionController {

    @ExceptionHandler(value = VideoNotFoundException.class)
    public ResponseEntity<?> handleVideoNotFoundException(VideoNotFoundException exception) {
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
    }
}
