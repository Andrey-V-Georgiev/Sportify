package com.softuni.sportify.domain.models.service_models;

public class LocationServiceModel extends BaseServiceModel {

    private SportCenterServiceModel sportCenter;
    private HallServiceModel hall;

    public LocationServiceModel() {
    }

    public SportCenterServiceModel getSportCenter() {
        return sportCenter;
    }

    public void setSportCenter(SportCenterServiceModel sportCenter) {
        this.sportCenter = sportCenter;
    }

    public HallServiceModel getHall() {
        return hall;
    }

    public void setHall(HallServiceModel hall) {
        this.hall = hall;
    }
}
