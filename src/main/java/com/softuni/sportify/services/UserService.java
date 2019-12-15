package com.softuni.sportify.services;

import com.softuni.sportify.domain.models.service_models.UserServiceModel;
import com.softuni.sportify.exceptions.CreateException;
import com.softuni.sportify.exceptions.DeleteException;
import com.softuni.sportify.exceptions.ReadException;
import com.softuni.sportify.exceptions.UpdateException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService extends UserDetailsService {

    UserServiceModel registerUser(UserServiceModel userServiceModel) throws CreateException;

    List<UserServiceModel> findAllUsers();

    void deleteUser(String id) throws DeleteException;

    UserServiceModel findById(String id) throws ReadException;

    UserServiceModel changeUserAuthorities(String id, String authority) throws ReadException, UpdateException;
}
