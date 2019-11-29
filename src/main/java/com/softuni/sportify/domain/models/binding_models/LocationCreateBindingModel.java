package com.softuni.sportify.domain.models.binding_models;

public class LocationCreateBindingModel extends BaseBindingModel {

    private SportCenterCreateBindingModel sportCenter;
    private HallCreateBindingModel hall;

    public LocationCreateBindingModel() {
    }

    public SportCenterCreateBindingModel getSportCenter() {
        return sportCenter;
    }

    public void setSportCenter(SportCenterCreateBindingModel sportCenter) {
        this.sportCenter = sportCenter;
    }

    public HallCreateBindingModel getHall() {
        return hall;
    }

    public void setHall(HallCreateBindingModel hall) {
        this.hall = hall;
    }
}
