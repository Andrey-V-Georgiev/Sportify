package com.softuni.sportify.domain.models.service_models;

public abstract class BaseServiceModel {

    private String id;

    BaseServiceModel() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
