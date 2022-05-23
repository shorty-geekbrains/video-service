package ru.geekbrains.videoservice.exceptions;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class IncorrectLinkException extends RuntimeException {
    public IncorrectLinkException(String message) {
        super(message);
    }
}
