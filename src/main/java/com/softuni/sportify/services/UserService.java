package com.softuni.sportify.services;

import com.softuni.sportify.domain.models.service_models.UserServiceModel;
import org.springframework.stereotype.Service;

@Service
public interface UserService {

    UserServiceModel registerUser(UserServiceModel userServiceModel);
}
