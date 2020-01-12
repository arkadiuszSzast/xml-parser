package com.xml.parser.utils.exception;

public class PostDateNotFoundException extends RuntimeException{

    private static final String ERROR_MESSAGE = "Creation date for given post not found";

    public PostDateNotFoundException() {
        super(ERROR_MESSAGE);
    }
}
