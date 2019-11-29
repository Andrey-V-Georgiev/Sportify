package com.softuni.sportify.domain.models.binding_models;

public class HallCreateBindingModel extends BaseBindingModel {

    private String name;
    private String section;
    private int floor;

    public HallCreateBindingModel() {
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
