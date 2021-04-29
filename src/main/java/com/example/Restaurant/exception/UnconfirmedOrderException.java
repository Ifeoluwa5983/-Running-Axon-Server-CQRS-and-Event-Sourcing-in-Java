package com.example.Restaurant.exception;

public class UnconfirmedOrderException extends Exception {
    public UnconfirmedOrderException() {
    }

    public UnconfirmedOrderException(String message) {
        super(message);
    }

    public UnconfirmedOrderException(String message, Throwable cause) {
        super(message, cause);
    }

    public UnconfirmedOrderException(Throwable cause) {
        super(cause);
    }

    public UnconfirmedOrderException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
