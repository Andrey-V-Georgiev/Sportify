package com.softuni.sportify.web.controllers;

import com.softuni.sportify.domain.models.binding_models.UserEditBindingModel;
import com.softuni.sportify.domain.models.view_models.UserViewModel;
import com.softuni.sportify.exceptions.DeleteException;
import com.softuni.sportify.exceptions.ReadException;
import com.softuni.sportify.exceptions.UpdateException;
import com.softuni.sportify.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

import static com.softuni.sportify.constants.AuthConstants.HAS_ROLE_ADMIN;
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
    @PreAuthorize(HAS_ROLE_ADMIN)
    public ModelAndView showAllUsers(ModelAndView modelAndView) {

        List<UserViewModel> userViewModels = this.userService.findAllUsers()
                .stream()
                .map(u -> this.modelMapper.map(u, UserViewModel.class))
                .collect(Collectors.toList());

        modelAndView.addObject("userViewModels", userViewModels);

        modelAndView.setViewName(VIEW_SHOW_ALL_USERS);
        return modelAndView;
    }

    @GetMapping("/edit-user/{id}")
    @PreAuthorize(HAS_ROLE_ADMIN)
    public ModelAndView editUser(
            @PathVariable String id,
            ModelAndView modelAndView) throws ReadException {

        UserViewModel userViewModel = this.modelMapper.map(this.userService.findById(id), UserViewModel.class);
        modelAndView.addObject("userViewModel", userViewModel);
        modelAndView.addObject("userEditBindingModel", new UserEditBindingModel());
        modelAndView.setViewName(VIEW_EDIT_USER);
        return modelAndView;
    }

    @PostMapping("/edit-user/{id}")
    @PreAuthorize(HAS_ROLE_ADMIN)
    public ModelAndView editUserConfirm(
            @PathVariable String id,
            @Valid
            @ModelAttribute UserEditBindingModel userEditBindingModel,
            BindingResult userBindingResult,
            ModelAndView modelAndView) throws ReadException, UpdateException {

        if(userBindingResult.hasErrors()) {
            UserViewModel userViewModel = this.modelMapper.map(this.userService.findById(id), UserViewModel.class);
            modelAndView.addObject("userViewModel", userViewModel);
            modelAndView.addObject("userEditBindingModel", userEditBindingModel);
            modelAndView.setViewName(VIEW_EDIT_USER);
            return modelAndView;
        }

        this.userService.changeUserAuthorities(id, userEditBindingModel.getAuthority());

        modelAndView.setViewName(REDIRECT_TO_SHOW_ALL_USERS);
        return modelAndView;
    }

    @PostMapping("/delete-user/{id}")
    @PreAuthorize(HAS_ROLE_ADMIN)
    public ModelAndView deleteUser(
            @PathVariable String id,
            ModelAndView modelAndView) throws DeleteException {

        this.userService.deleteUser(id);

        modelAndView.setViewName(REDIRECT_TO_SHOW_ALL_USERS);
        return modelAndView;
    }
}
