package com.softuni.sportify.domain.models.binding_models;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class AddressCreateBindingModel extends BaseBindingModel{

    private String country;
    private String city;
    private String street;
    private String details;

    public AddressCreateBindingModel() {
        this.country = "";
        this.city = "";
        this.street = "";
        this.details = "";
    }

    @NotNull
    @Size(min = 3, max = 40)
    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @NotNull
    @Size(min = 2, max = 40)
    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @NotNull
    @Size(min = 2, max = 40)
    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    @NotNull
    @Size(min = 8, max = 200)
    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }
}
