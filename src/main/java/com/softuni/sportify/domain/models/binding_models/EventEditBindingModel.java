package com.softuni.sportify.domain.models.binding_models;

import com.softuni.sportify.constants.Level;
import com.softuni.sportify.domain.entities.Location;
import com.softuni.sportify.domain.entities.Sport;

import java.time.LocalDateTime;

public class EventEditBindingModel extends BaseBindingModel {

    private String name;
    private Sport sport;
    private Level level;
    private Location location;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private int maxCapacity;
    private int freePlaces;

    public EventEditBindingModel() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Sport getSport() {
        return sport;
    }

    public void setSport(Sport sport) {
        this.sport = sport;
    }

    public Level getLevel() {
        return level;
    }

    public void setLevel(Level level) {
        this.level = level;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public int getMaxCapacity() {
        return maxCapacity;
    }

    public void setMaxCapacity(int maxCapacity) {
        this.maxCapacity = maxCapacity;
    }

    public int getFreePlaces() {
        return freePlaces;
    }

    public void setFreePlaces(int freePlaces) {
        this.freePlaces = freePlaces;
    }
}
