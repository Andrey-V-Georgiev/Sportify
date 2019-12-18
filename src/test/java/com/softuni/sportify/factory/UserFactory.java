package com.softuni.sportify.factory;

import com.softuni.sportify.domain.models.service_models.UserServiceModel;

import java.util.HashSet;

public abstract class UserFactory {

    public static UserServiceModel createValidUserServiceModel() {

        UserServiceModel userServiceModel = new UserServiceModel();

        userServiceModel.setUsername("username");
        userServiceModel.setEmail("email@email.com");
        userServiceModel.setPassword("pasword");
        userServiceModel.setAuthorities(new HashSet<>());

        return userServiceModel;
    }
}
