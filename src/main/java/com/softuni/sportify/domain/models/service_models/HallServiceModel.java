package com.softuni.sportify.domain.models.service_models;

public class HallServiceModel extends BaseServiceModel {

    private String name;
    private String section;
    private int floor;

    public HallServiceModel() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public int getFloor() {
        return floor;
    }

    public void setFloor(int floor) {
        this.floor = floor;
    }
}
