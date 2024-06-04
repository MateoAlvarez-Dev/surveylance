package com.riwi.surveylance.util.exceptions;

public class NoOptionsInClosedQuestion extends RuntimeException {
    private static final String ERROR_MESSAGE = "You need to provide the answer options if is a closed question.";

    public NoOptionsInClosedQuestion() {
        super(String.format(ERROR_MESSAGE));
    }
}
