package com.softuni.sportify.domain.entities;


import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "sports")
public class Sport extends BaseEntity {

    private String name;
    private Set<Image> images;
    private Set<SportCenter> sportCenters;

    public Sport() {
    }

    @Column(name = "name", nullable = false)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @OneToMany(cascade= CascadeType.ALL)
    @JoinColumn(name="sport_id", nullable=false)
    public Set<Image> getImages() {
        return images;
    }

    public void setImages(Set<Image> images) {
        this.images = images;
    }

    @ManyToMany(mappedBy = "sports")
    public Set<SportCenter> getSportCenters() {
        return sportCenters;
    }

    public void setSportCenters(Set<SportCenter> sportCenters) {
        this.sportCenters = sportCenters;
    }
}
