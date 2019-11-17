package com.softuni.sportify.domain.models.service_models;

public class RoleServiceModel extends BaseServiceModel{

    private String authority;

    public RoleServiceModel() {
    }

    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }
}
