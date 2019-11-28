package com.softuni.sportify.services;

import com.softuni.sportify.domain.entities.Role;
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
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static com.softuni.sportify.constants.RoleConstants.*;

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
    public List<UserServiceModel> findAllUsers() {

        return this.userRepository.findAll()
                .stream()
                .map(u-> this.modelMapper.map(u, UserServiceModel.class))
                .collect(Collectors.toList());
    }

    @Override
    public void deleteUser(String id) {
        this.userRepository.deleteById(id);
    }

    @Override
    public UserServiceModel findById(String id) {

        User user = this.userRepository.findById(id).orElse(null);
        return this.modelMapper.map(user, UserServiceModel.class);
    }

    @Override
    public UserServiceModel changeUserAuthorities(String id, String authority) {

        User user = this.userRepository.findById(id).orElse(null);
        Set<Role> newAuthorities = new LinkedHashSet<>();
        switch(authority) {
            case ROLE_USER:
                newAuthorities.add(this.modelMapper.map(this.roleService.findByAuthority(ROLE_USER), Role.class));
                break;
            case ROLE_ADMIN:
                newAuthorities.add(this.modelMapper.map(this.roleService.findByAuthority(ROLE_USER), Role.class));
                newAuthorities.add(this.modelMapper.map(this.roleService.findByAuthority(ROLE_ADMIN), Role.class));
                break;
        }
        user.setAuthorities(newAuthorities);
        User updatedUser = this.userRepository.saveAndFlush(user);
        return this.modelMapper.map(updatedUser, UserServiceModel.class);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return this.userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Username not found!"));
    }
}
