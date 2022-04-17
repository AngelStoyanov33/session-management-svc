package me.angelstoyanov.sporton.management.session.exception;

public class SessionNotExistsException extends RuntimeException {
    public SessionNotExistsException(String message) {
        super(message);
    }

    public SessionNotExistsException(String message, Throwable cause) {
        super(message, cause);
    }

}
