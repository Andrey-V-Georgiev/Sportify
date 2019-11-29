package com.softuni.sportify.domain.models.binding_models;

import java.util.ArrayList;
import java.util.List;

public class UpdateSportCenterSportsBindingModel extends BaseBindingModel {

    private List<String> spotrsIDs;

    public UpdateSportCenterSportsBindingModel() {
        this.spotrsIDs = new ArrayList<>();
    }

    public List<String> getSpotrsIDs() {
        return spotrsIDs;
    }

    public void setSpotrsIDs(List<String> spotrsIDs) {
        this.spotrsIDs = spotrsIDs;
    }
}
