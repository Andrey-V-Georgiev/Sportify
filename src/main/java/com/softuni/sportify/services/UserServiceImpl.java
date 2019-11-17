package com.softuni.sportify.services;

import com.softuni.sportify.domain.entities.Role;
import com.softuni.sportify.domain.entities.User;
import com.softuni.sportify.domain.models.service_models.UserServiceModel;
import com.softuni.sportify.repositories.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.LinkedHashSet;
import java.util.Set;
import java.util.stream.Collectors;

import static com.softuni.sportify.services.RoleServiceImpl.ROLE_USER;

@Service
public class UserServiceImpl implements UserService {

    private final ModelMapper modelMapper;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final UserRepository userRepository;
    private final RoleService roleService;

    @Autowired
    public UserServiceImpl(ModelMapper modelMapper,
                           BCryptPasswordEncoder bCryptPasswordEncoder,
                           UserRepository userRepository,
                           RoleService roleService) {
        this.modelMapper = modelMapper;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.userRepository = userRepository;
        this.roleService = roleService;
    }

    @Override
    public UserServiceModel registerUser(UserServiceModel userServiceModel) {
        this.roleService.initialRolesSeed();

        if (this.userRepository.count() == 0) {
            Set<String> authorities = this.roleService.findAllRoles()
                    .stream()
                    .map(r -> this.modelMapper.map(r, String.class))
                    .collect(Collectors.toSet());
            userServiceModel.setAuthorities(authorities);
        } else {
            userServiceModel.setAuthorities(new LinkedHashSet<>());
            userServiceModel.getAuthorities().add(ROLE_USER);
        }

        User user = this.modelMapper.map(userServiceModel, User.class);
        user.setPassword(this.bCryptPasswordEncoder.encode(userServiceModel.getPassword()));

        return this.modelMapper.map(this.userRepository.saveAndFlush(user), UserServiceModel.class);
    }
}
