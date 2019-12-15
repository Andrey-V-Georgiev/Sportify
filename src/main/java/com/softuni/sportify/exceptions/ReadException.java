package com.softuni.sportify.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class ReadException extends Exception {

    public ReadException() {
    }

    public ReadException(String message) {
        super(message);
    }
}
