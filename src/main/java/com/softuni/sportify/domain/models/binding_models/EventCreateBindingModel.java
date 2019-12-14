package com.softuni.sportify.domain.models.binding_models;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class EventCreateBindingModel extends BaseBindingModel {

    private String sport;
    private String level;
    private int floor;
    private String hall;
    private String startTime;
    private int maxCapacity;

    public EventCreateBindingModel() {
    }

    @NotNull
    @Size(min = 2, max = 30)
    public String getSport() {
        return sport;
    }

    public void setSport(String sport) {
        this.sport = sport;
    }

    @NotNull
    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    @NotNull
    @Min(-2)
    @Max(10)
    public int getFloor() {
        return floor;
    }

    public void setFloor(int floor) {
        this.floor = floor;
    }

    @NotNull
    @Size(min = 2, max = 30)
    public String getHall() {
        return hall;
    }

    public void setHall(String hall) {
        this.hall = hall;
    }

    @NotNull
    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    @NotNull
    @Min(1)
    @Max(100)
    public int getMaxCapacity() {
        return maxCapacity;
    }

    public void setMaxCapacity(int maxCapacity) {
        this.maxCapacity = maxCapacity;
    }
}
