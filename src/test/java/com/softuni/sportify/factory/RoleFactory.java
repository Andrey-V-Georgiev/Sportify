package com.softuni.sportify.factory;

import com.softuni.sportify.domain.models.service_models.RoleServiceModel;

import static com.softuni.sportify.constants.RoleConstants.ROLE_ADMIN;
import static com.softuni.sportify.constants.RoleConstants.ROLE_USER;

public abstract class RoleFactory {

    public static RoleServiceModel createUserRoleServiceModel() {

        RoleServiceModel roleServiceModel = new RoleServiceModel();
        roleServiceModel.setAuthority(ROLE_USER);
        return roleServiceModel;
    }

    public static RoleServiceModel createAdminRoleServiceModel() {

        RoleServiceModel roleServiceModel = new RoleServiceModel();
        roleServiceModel.setAuthority(ROLE_ADMIN);
        return roleServiceModel;
    }
}
