package com.softuni.sportify.domain.models.binding_models;

public abstract class BaseBindingModel {

    private String id;

    BaseBindingModel() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
