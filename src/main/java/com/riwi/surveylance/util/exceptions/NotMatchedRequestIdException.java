package com.riwi.surveylance.util.exceptions;

public class NotMatchedRequestIdException extends RuntimeException {

    private static final String ERROR_MESSAGE = "The request id is not matched";

    public NotMatchedRequestIdException() {
        super(ERROR_MESSAGE);
    }

    public NotMatchedRequestIdException(String message) {
        super(message);
    }
    
}