package com.softuni.sportify.services;

import com.softuni.sportify.domain.models.service_models.UserServiceModel;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService extends UserDetailsService {

    UserServiceModel registerUser(UserServiceModel userServiceModel);

    List<UserServiceModel> findAllUsers();

    void deleteUser(String id);

    UserServiceModel findById(String id);

    UserServiceModel changeUserAuthorities(String id, String authority);
}
