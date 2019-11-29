package com.softuni.sportify.domain.models.binding_models;

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

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }
}
