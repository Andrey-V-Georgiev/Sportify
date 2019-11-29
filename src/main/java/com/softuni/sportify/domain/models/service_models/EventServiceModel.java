package com.softuni.sportify.domain.models.service_models;

import com.softuni.sportify.constants.Level;

import java.time.LocalDateTime;

public class EventServiceModel extends BaseServiceModel {

    private String name;
    private SportServiceModel sport;
    private Level level;
    private LocationServiceModel location;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private int maxCapacity;
    private int freePlaces;

    public EventServiceModel() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public SportServiceModel getSport() {
        return sport;
    }

    public void setSport(SportServiceModel sport) {
        this.sport = sport;
    }

    public Level getLevel() {
        return level;
    }

    public void setLevel(Level level) {
        this.level = level;
    }

    public LocationServiceModel getLocation() {
        return location;
    }

    public void setLocation(LocationServiceModel location) {
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
