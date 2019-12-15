package com.softuni.sportify.web.exception_handling;

import com.softuni.sportify.exceptions.*;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({CreateException.class})
    public ModelAndView handleCreateException(Exception e) {

        ModelAndView modelAndView = new ModelAndView("exceptions/all-exceptions-page");
        modelAndView.addObject("message", e.getMessage());

        return modelAndView;
    }

    @ExceptionHandler({ReadException.class})
    public ModelAndView handleReadException(Exception e) {

        ModelAndView modelAndView = new ModelAndView("exceptions/all-exceptions-page");
        modelAndView.addObject("message", e.getMessage());

        return modelAndView;
    }

    @ExceptionHandler({UpdateException.class})
    public ModelAndView handleUpdateException(Exception e) {

        ModelAndView modelAndView = new ModelAndView("exceptions/all-exceptions-page");
        modelAndView.addObject("message", e.getMessage());

        return modelAndView;
    }

    @ExceptionHandler({DeleteException.class})
    public ModelAndView handleDeleteException(Exception e) {

        ModelAndView modelAndView = new ModelAndView("exceptions/all-exceptions-page");
        modelAndView.addObject("message", e.getMessage());

        return modelAndView;
    }

    @ExceptionHandler({Exception.class})
    public ModelAndView handleEx(Exception e) {

        ModelAndView modelAndView = new ModelAndView("exceptions/all-exceptions-page");
        modelAndView.addObject("message", e.getMessage());

        return modelAndView;
    }
}
