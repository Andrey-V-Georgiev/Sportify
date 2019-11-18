package com.softuni.sportify.config;

import com.softuni.sportify.domain.entities.Role;
import com.softuni.sportify.domain.entities.User;
import com.softuni.sportify.domain.models.service_models.UserServiceModel;
import com.softuni.sportify.repositories.RoleRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Configuration
public class ModelMapperConfig {

    private final RoleRepository roleRepository;

    @Autowired
    public ModelMapperConfig(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Bean
    public ModelMapper modelMapper() {

        ModelMapper modelMapper = new ModelMapper();
//        modelMapper.createTypeMap(UserServiceModel.class, User.class)
//                .addMapping(
//                        (userServiceModel) -> userServiceModel.getId(),
//                        (user, value) -> user.setId(String.valueOf(value))
//                )
//                .addMapping(
//                        (userServiceModel) -> userServiceModel.getUsername(),
//                        (user, value) -> user.setUsername(String.valueOf(value))
//                )
//                .addMapping(
//                        (userServiceModel) -> userServiceModel.getPassword(),
//                        (user, value) -> user.setPassword(String.valueOf(value))
//                )
//                .addMapping(
//                        (userServiceModel) -> userServiceModel.getEmail(),
//                        (user, value) -> user.setEmail(String.valueOf(value))
//                )
//                .addMapping(
//                        (userServiceModel) -> userServiceModel.getAuthorities(),
//                        (user, value) -> {
//                            Set<Role> roleSet = Stream.of(value)
//                                    .map(r -> this.roleRepository.findByAuthority((String) r))
//                                    .collect(Collectors.toSet());
//                            user.setAuthorities(roleSet);
//                        });

        return modelMapper;
    }
}
