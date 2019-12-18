package com.softuni.sportify.services;

import com.softuni.sportify.domain.entities.Role;
import com.softuni.sportify.domain.entities.User;
import com.softuni.sportify.domain.models.service_models.RoleServiceModel;
import com.softuni.sportify.domain.models.service_models.UserServiceModel;
import com.softuni.sportify.exceptions.*;
import com.softuni.sportify.repositories.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static com.softuni.sportify.constants.RoleConstants.*;
import static com.softuni.sportify.constants.ExceptionConstants.*;

@Service
public class UserServiceImpl implements UserService {

    private final ModelMapper modelMapper;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final UserRepository userRepository;
    private final RoleService roleService;
    private final Validator validator;

    @Autowired
    public UserServiceImpl(ModelMapper modelMapper,
                           BCryptPasswordEncoder bCryptPasswordEncoder,
                           UserRepository userRepository,
                           RoleService roleService,
                           Validator validator) {
        this.modelMapper = modelMapper;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.userRepository = userRepository;
        this.roleService = roleService;
        this.validator = validator;
    }

    @Override
    public UserServiceModel registerUser(UserServiceModel userServiceModel) throws CreateException {
        this.roleService.initialRolesSeed();

        if (this.userRepository.count() == 0) {
            Set<RoleServiceModel> authorities = this.roleService.findAllRoles()
                    .stream()
                    .map(r -> this.modelMapper.map(r, RoleServiceModel.class))
                    .collect(Collectors.toSet());
            userServiceModel.setAuthorities(authorities);
        } else {
            userServiceModel.setAuthorities(new LinkedHashSet<>());
            RoleServiceModel roleServiceModel = this.roleService.findByAuthority(ROLE_USER);
            userServiceModel.getAuthorities().add(roleServiceModel);
        }
        Set<ConstraintViolation<UserServiceModel>> validateResult = validator.validate(userServiceModel);
        if (!validateResult.isEmpty()) {
            throw new CreateException(USER_CREATE_EXCEPTION_MSG);
        }
        User user = this.modelMapper.map(userServiceModel, User.class);
        user.setPassword(this.bCryptPasswordEncoder.encode(userServiceModel.getPassword()));
        User savedUser = this.userRepository.saveAndFlush(user);

        return this.modelMapper.map(savedUser, UserServiceModel.class);
    }

    @Override
    public List<UserServiceModel> findAllUsers() {

        return this.userRepository.findAll()
                .stream()
                .map(u -> this.modelMapper.map(u, UserServiceModel.class))
                .collect(Collectors.toList());
    }

    @Override
    public void deleteUser(String id) throws DeleteException {
        try {
            this.userRepository.deleteById(id);
        } catch (Exception e) {
            throw new DeleteException(USER_DELETE_EXCEPTION_MSG);
        }
    }

    @Override
    public UserServiceModel findById(String id) throws ReadException {

        User user = this.userRepository.findById(id)
                .orElseThrow(() -> new ReadException(USER_READ_EXCEPTION_MSG));
        return this.modelMapper.map(user, UserServiceModel.class);
    }

    @Override
    public UserServiceModel changeUserAuthorities(String id, String authority) throws ReadException, UpdateException {

        User user = this.userRepository.findById(id)
                .orElseThrow(() -> new UpdateException(USER_UPDATE_EXCEPTION_MSG));
        if(authority == null){
            throw new UpdateException(USER_UPDATE_EXCEPTION_MSG);
        }
        Set<Role> newAuthorities = new LinkedHashSet<>();
        switch (authority) {
            case ROLE_USER:
                newAuthorities.add(this.modelMapper.map(this.roleService.findByAuthority(ROLE_USER), Role.class));
                break;
            case ROLE_ADMIN:
                newAuthorities.add(this.modelMapper.map(this.roleService.findByAuthority(ROLE_USER), Role.class));
                newAuthorities.add(this.modelMapper.map(this.roleService.findByAuthority(ROLE_ADMIN), Role.class));
                break;
            default:
                newAuthorities = null;
                break;
        }
        user.setAuthorities(newAuthorities);
        UserServiceModel userServiceModel = this.modelMapper.map(user, UserServiceModel.class);
        if (!validator.validate(userServiceModel).isEmpty()) {
            throw new UpdateException(USER_UPDATE_EXCEPTION_MSG);
        }
        User updatedUser = this.userRepository.saveAndFlush(user);
        return this.modelMapper.map(updatedUser, UserServiceModel.class);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return  this.userRepository.findByUsername(username).orElse(null);
    }
}
