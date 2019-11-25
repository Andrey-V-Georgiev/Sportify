package com.softuni.sportify.domain.entities;


import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "sports")
public class Sport extends BaseEntity {

    private String name;
    private String description;
    private Set<Image> images;
    private Set<SportCenter> sportCenters;

    public Sport() {
        this.images = new LinkedHashSet<>();
        this.sportCenters = new LinkedHashSet<>();
    }

    @Column(name = "name", nullable = false)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "description", nullable = false, columnDefinition = "TEXT")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @ManyToMany(targetEntity = Image.class, fetch = FetchType.EAGER)
    @JoinTable(
            name="sport_images",
            joinColumns = {@JoinColumn( name="sport_id")},
            inverseJoinColumns = {@JoinColumn( name="image_id")}
    )
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
