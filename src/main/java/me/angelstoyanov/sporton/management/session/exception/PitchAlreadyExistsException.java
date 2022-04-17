package me.angelstoyanov.sporton.management.session.exception;

public class PitchAlreadyExistsException extends RuntimeException {
    public PitchAlreadyExistsException(String message) {
        super(message);
    }

    public PitchAlreadyExistsException(String message, Throwable cause) {
        super(message, cause);
    }
}
