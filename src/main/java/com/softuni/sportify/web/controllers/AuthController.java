package com.softuni.sportify.web.controllers;

import com.softuni.sportify.domain.models.binding_models.UserRegisterBindingModel;
import com.softuni.sportify.domain.models.service_models.UserServiceModel;
import com.softuni.sportify.services.UserService;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import static com.softuni.sportify.constants.AuthConstants.IS_ANONYMOUS;
import static com.softuni.sportify.constants.AuthControllerConstants.*;

@Controller
public class AuthController {

    private final UserService userService;
    private final ModelMapper modelMapper;

    @Autowired
    public AuthController(UserService userService,
                          ModelMapper modelMapper) {
        this.userService = userService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/register")
    @PreAuthorize(IS_ANONYMOUS)
    public ModelAndView register(ModelAndView modelAndView) {

        modelAndView.setViewName(VIEW_REGISTER);
        return modelAndView;
    }

    @PostMapping("/register")
    @PreAuthorize(IS_ANONYMOUS)
    public ModelAndView registerConfirm(
            @ModelAttribute(name = "model") UserRegisterBindingModel model,
            BindingResult bindingResult,
            ModelAndView modelAndView) {

        UserServiceModel userServiceModel = this.modelMapper.map(model, UserServiceModel.class);
        this.userService.registerUser(userServiceModel);

        modelAndView.setViewName(REDIRECT_TO_LOGIN);
        return modelAndView;
    }

    @GetMapping("/login")
    @PreAuthorize(IS_ANONYMOUS)
    public ModelAndView login(ModelAndView modelAndView) {

        modelAndView.setViewName(VIEW_LOGIN);
        return modelAndView;
    }
}
