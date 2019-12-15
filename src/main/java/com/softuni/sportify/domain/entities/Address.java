package com.softuni.sportify.domain.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.Size;

@Entity
@Table(name = "addresses")
public class Address extends BaseEntity {

    private String country;
    private String city;
    private String street;
    private String details;

    public Address() {
    }

    @Column(name = "country", nullable = false)
    @Size(min = 3, max = 40)
    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Column(name = "city", nullable = false)
    @Size(min = 2, max = 40)
    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Column(name = "street", nullable = false)
    @Size(min = 2, max = 40)
    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    @Column(name = "description", nullable = false, columnDefinition = "TEXT")
    @Size(min = 8, max = 200)
    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }
}
