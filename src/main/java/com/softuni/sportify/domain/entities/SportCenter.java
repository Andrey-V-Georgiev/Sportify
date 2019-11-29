package com.softuni.sportify.domain.entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "sport_centers")
public class SportCenter extends BaseEntity {

    private String name;
    private Address address;
    private String description;
    private Image descriptionImage;
    private Image iconImage;
    private List<Image> sportCenterImages;
    private List<Sport> sports;
    private List<Event> events;

    public SportCenter() {
        this.sportCenterImages = new ArrayList<>();
        this.sports = new ArrayList<>();
        this.events = new ArrayList<>();
    }

    @Column(name = "name", nullable = false)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @ManyToOne(optional = false)
    @JoinColumn(name="sport_center_id")
    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    @Column(name = "sport_center_description", nullable = false, columnDefinition = "TEXT")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @ManyToOne(optional = false)
    @JoinColumn(name="sport_center_description_image_id")
    public Image getDescriptionImage() {
        return descriptionImage;
    }

    public void setDescriptionImage(Image descriptionImage) {
        this.descriptionImage = descriptionImage;
    }

    @ManyToOne(optional = false)
    @JoinColumn(name="sport_center_icon_image_id")
    public Image getIconImage() {
        return iconImage;
    }

    public void setIconImage(Image iconImage) {
        this.iconImage = iconImage;
    }

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "sport_center_images",
            joinColumns = @JoinColumn(name = "sport_center_id"),
            inverseJoinColumns = @JoinColumn(name = "image_id")
    )
    public List<Image> getSportCenterImages() {
        return sportCenterImages;
    }

    public void setSportCenterImages(List<Image> sportCenterImages) {
        this.sportCenterImages = sportCenterImages;
    }

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "sport_center_sports",
            joinColumns = @JoinColumn(name = "sport_center_id"),
            inverseJoinColumns = @JoinColumn(name = "sport_id")
    )
    public List<Sport> getSports() {
        return sports;
    }

    public void setSports(List<Sport> sports) {
        this.sports = sports;
    }

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "sport_center_id", nullable = false)
    public List<Event> getEvents() {
        return events;
    }

    public void setEvents(List<Event> events) {
        this.events = events;
    }

}
