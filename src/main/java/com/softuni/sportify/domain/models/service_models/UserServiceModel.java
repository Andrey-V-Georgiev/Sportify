package com.softuni.sportify.domain.models.service_models;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Set;

public class UserServiceModel extends BaseServiceModel {

    private String username;
    private String password;
    private String email;
    private Set<RoleServiceModel> authorities;

    public UserServiceModel() {
    }

    @NotNull
    @Size(min = 2, max = 30)
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @NotNull
    @Size(min = 1, max = 200)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @NotNull
    @Size(min = 2, max = 60)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @NotNull
    public Set<RoleServiceModel> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(Set<RoleServiceModel> authorities) {
        this.authorities = authorities;
    }
}
