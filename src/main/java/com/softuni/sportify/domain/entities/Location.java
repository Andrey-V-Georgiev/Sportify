package com.softuni.sportify.domain.entities;

import javax.persistence.*;

@Entity
@Table(name = "locations")
public class Location extends BaseEntity {

    private SportCenter sportCenter;
    private Hall hall;

    public Location() {
    }

    @ManyToOne(optional = false)
    @JoinColumn(name="sport_center_id", nullable=false)
    public SportCenter getSportCenter() {
        return sportCenter;
    }

    public void setSportCenter(SportCenter sportCenter) {
        this.sportCenter = sportCenter;
    }

    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    public Hall getHall() {
        return hall;
    }

    public void setHall(Hall hall) {
        this.hall = hall;
    }
}
