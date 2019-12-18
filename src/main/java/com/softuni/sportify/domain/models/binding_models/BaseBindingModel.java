package com.softuni.sportify.domain.models.binding_models;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

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
