package com.softuni.sportify.services;

import com.softuni.sportify.domain.entities.Role;
import com.softuni.sportify.domain.models.service_models.RoleServiceModel;
import com.softuni.sportify.repositories.RoleRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class RoleServiceImpl implements RoleService {

    static final String ROLE_USER = "ROLE_USER";
    static final String ROLE_ADMIN = "ROLE_ADMIN";
    static final String ROLE_ROOT = "ROLE_ROOT";

    private final ModelMapper modelMapper;
    private final RoleRepository roleRepository;

    @Autowired
    public RoleServiceImpl(ModelMapper modelMapper, RoleRepository roleRepository) {
        this.modelMapper = modelMapper;
        this.roleRepository = roleRepository;
    }

    @Override
    public void initialRolesSeed() {
        if (this.roleRepository.count() == 0) {
            this.roleRepository.saveAndFlush(new Role(ROLE_USER));
            this.roleRepository.saveAndFlush(new Role(ROLE_ADMIN));
            this.roleRepository.saveAndFlush(new Role(ROLE_ROOT));
        }
    }

    @Override
    public Set<RoleServiceModel> findAllRoles() {
        return this.roleRepository.findAll()
                .stream()
                .map(r -> this.modelMapper.map(r, RoleServiceModel.class))
                .collect(Collectors.toSet());
    }

    @Override
    public RoleServiceModel findByAuthority(String authority) {
        return this.modelMapper.map(this.roleRepository.findByAuthority(authority), RoleServiceModel.class);
    }
}
