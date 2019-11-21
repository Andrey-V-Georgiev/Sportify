package com.softuni.sportify.domain.entities;

import java.util.Set;

public class SportCenter extends BaseEntity {

    private String name;
    private Location location;
    private Set<Sport> sports;
    private Set<Event> calendar;
    private Set<Image> images;

    public SportCenter() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public Set<Sport> getSports() {
        return sports;
    }

    public void setSports(Set<Sport> sports) {
        this.sports = sports;
    }

    public Set<Event> getCalendar() {
        return calendar;
    }

    public void setCalendar(Set<Event> calendar) {
        this.calendar = calendar;
    }

    public Set<Image> getImages() {
        return images;
    }

    public void setImages(Set<Image> images) {
        this.images = images;
    }
}
