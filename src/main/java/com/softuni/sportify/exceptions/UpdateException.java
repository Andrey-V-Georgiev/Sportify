package com.softuni.sportify.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class UpdateException extends Exception {


    public UpdateException() {
    }

    public UpdateException(String message) {
        super(message);
    }
}

