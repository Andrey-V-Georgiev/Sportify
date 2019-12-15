package com.softuni.sportify.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class DeleteException extends Exception {

    public DeleteException() {
    }

    public DeleteException(String message) {
        super(message);
    }
}
