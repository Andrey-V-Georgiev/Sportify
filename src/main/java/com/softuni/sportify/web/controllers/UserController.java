package com.softuni.sportify.web.controllers;

import com.softuni.sportify.domain.models.binding_models.UserEditBindingModel;
import com.softuni.sportify.domain.models.service_models.UserServiceModel;
import com.softuni.sportify.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

import static com.softuni.sportify.constants.UserControllerConstants.*;

@Controller
@RequestMapping("/users")
public class UserController {

    private final UserService userService;
    private final ModelMapper modelMapper;

    @Autowired
    public UserController(UserService userService,
                          ModelMapper modelMapper) {
        this.userService = userService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/show-all-users")
    public ModelAndView showAllUsers(ModelAndView modelAndView) {

        List<UserServiceModel> allUserServiceModels = this.userService.findAllUsers();
        modelAndView.addObject("allUserServiceModels", allUserServiceModels);


        modelAndView.setViewName(VIEW_SHOW_ALL_USERS);
        return modelAndView;
    }

    @GetMapping("/edit-user/{id}")
    public ModelAndView editUser(@PathVariable String id,
                                 ModelAndView modelAndView) {

        UserServiceModel userServiceModel = this.userService.findById(id);
        modelAndView.addObject("userServiceModel", userServiceModel);
        modelAndView.setViewName(VIEW_EDIT_USER);
        return modelAndView;
    }

    @PostMapping("/edit-user/{id}")
    public ModelAndView editUserConfirm(@PathVariable String id,
                                 @ModelAttribute UserEditBindingModel userEditBindingModel,
                                 ModelAndView modelAndView) {

        this.userService.changeUserAuthorities(id, userEditBindingModel.getAuthority());
        modelAndView.setViewName(REDIRECT_TO_SHOW_ALL_USERS);
        return modelAndView;
    }

    @PostMapping("/delete-user/{id}")
    public ModelAndView deleteUser(@PathVariable String id,
                                   ModelAndView modelAndView) {
        this.userService.deleteUser(id);
        modelAndView.setViewName(REDIRECT_TO_SHOW_ALL_USERS);
        return modelAndView;
    }
}
