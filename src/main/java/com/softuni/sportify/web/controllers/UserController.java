package com.softuni.sportify.web.controllers;

import com.softuni.sportify.domain.models.service_models.UserServiceModel;
import com.softuni.sportify.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

import static com.softuni.sportify.constants.UserControllerConstants.*;

@Controller
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/show-all-users")
    public ModelAndView showAllUsers(ModelAndView modelAndView) {

        List<UserServiceModel> allUserServiceModels = this.userService.findAllUsers();
        modelAndView.addObject("allUserServiceModels", allUserServiceModels);


        modelAndView.setViewName(VIEW_SHOW_ALL_USERS);
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
