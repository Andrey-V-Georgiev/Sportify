package com.softuni.sportify.domain.entities;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "sport_centers")
public class SportCenter extends BaseEntity {

    private String name;
    private Address address;
    private Set<Sport> sports;
    private Set<Event> events;
    private Set<Image> images;

    public SportCenter() {
    }

    @Column(name = "name", nullable = false)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @ManyToOne(optional = false)
    @JoinColumn(name="sport_center_id")
    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "sport_center_sports",
            joinColumns = @JoinColumn(name = "sport_center_id"),
            inverseJoinColumns = @JoinColumn(name = "sport_id")
    )
    public Set<Sport> getSports() {
        return sports;
    }

    public void setSports(Set<Sport> sports) {
        this.sports = sports;
    }

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "sport_center_id", nullable = false)
    public Set<Event> getEvents() {
        return events;
    }

    public void setEvents(Set<Event> events) {
        this.events = events;
    }

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "sport_center_images",
            joinColumns = @JoinColumn(name = "sport_center_id"),
            inverseJoinColumns = @JoinColumn(name = "image_id")
    )
    public Set<Image> getImages() {
        return images;
    }

    public void setImages(Set<Image> images) {
        this.images = images;
    }
}
