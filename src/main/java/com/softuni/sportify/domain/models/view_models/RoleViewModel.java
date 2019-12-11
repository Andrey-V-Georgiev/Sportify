package com.softuni.sportify.domain.models.view_models;

public class RoleViewModel extends BaseViewModel {

    private String authority;

    public RoleViewModel() {
    }

    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }
}
