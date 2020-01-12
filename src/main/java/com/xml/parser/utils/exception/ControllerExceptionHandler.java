package com.xml.parser.utils.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.xml.bind.JAXBException;

@ControllerAdvice
@Slf4j
public class ControllerExceptionHandler {


    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(JAXBException.class)
    public void handleJaxbException() {
        log.error("ERROR WHEN PARSING XML");
    }
}
