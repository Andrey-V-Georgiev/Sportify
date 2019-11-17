package com.softuni.sportify.domain.models.view_models;

public abstract class BaseViewModel {

    private String id;

    BaseViewModel() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
