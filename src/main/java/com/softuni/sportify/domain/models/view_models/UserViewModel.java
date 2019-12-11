package com.softuni.sportify.domain.models.view_models;

import java.util.Set;

public class UserViewModel extends BaseViewModel {

    private String username;
    private String password;
    private String email;
    private Set<RoleViewModel> authorities;

    public UserViewModel() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<RoleViewModel> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(Set<RoleViewModel> authorities) {
        this.authorities = authorities;
    }
}
