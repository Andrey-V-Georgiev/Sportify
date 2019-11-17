package com.softuni.sportify.services;

import com.softuni.sportify.domain.models.service_models.RoleServiceModel;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public interface RoleService {

    void initialRolesSeed();

    Set<RoleServiceModel> findAllRoles();

    RoleServiceModel findByAuthority(String authority);
}
