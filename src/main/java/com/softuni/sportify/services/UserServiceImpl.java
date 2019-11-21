package com.softuni.sportify.services;

import com.softuni.sportify.domain.entities.User;
import com.softuni.sportify.domain.models.service_models.RoleServiceModel;
import com.softuni.sportify.domain.models.service_models.UserServiceModel;
import com.softuni.sportify.repositories.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.LinkedHashSet;
import java.util.Set;
import java.util.stream.Collectors;

import static com.softuni.sportify.constants.RoleConstants.ROLE_USER;

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
            Set<RoleServiceModel> authorities = this.roleService.findAllRoles()
                    .stream()
                    .map(r-> this.modelMapper.map(r, RoleServiceModel.class))
                    .collect(Collectors.toSet());
            userServiceModel.setAuthorities(authorities);
        } else {
            userServiceModel.setAuthorities(new LinkedHashSet<>());
            RoleServiceModel roleServiceModel = this.roleService.findByAuthority(ROLE_USER);
            userServiceModel.getAuthorities().add(roleServiceModel);
        }

        User user = this.modelMapper.map(userServiceModel, User.class);
        user.setPassword(this.bCryptPasswordEncoder.encode(userServiceModel.getPassword()));

        User savedUser = this.userRepository.saveAndFlush(user);

        return this.modelMapper.map(savedUser, UserServiceModel.class);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return this.userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Username not found!"));
    }
}
