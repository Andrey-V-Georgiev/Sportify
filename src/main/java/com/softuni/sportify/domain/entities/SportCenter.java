package com.softuni.sportify.domain.entities;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Table(name = "sport_centers")
public class SportCenter extends BaseEntity {

    private String name;
    private Address address;
    private String description;
    private Image iconImage;
    private List<Image> sportCenterImages;
    private List<Sport> sports;
    private List<Schedule> calendar;

    public SportCenter() {
    }

    @Size(min = 2, max = 30)
    @Column(name = "name", nullable = false)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @ManyToOne(optional = false)
    @JoinColumn(name = "sport_center_id")
    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    @Column(name = "sport_center_description", columnDefinition = "TEXT", nullable = false)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @ManyToOne(optional = false)
    @JoinColumn(name = "sport_center_icon_image_id")
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

    @ManyToMany(cascade = CascadeType.ALL, targetEntity = Sport.class)
    @JoinTable(
            name="sport_center_sports",
            joinColumns = {@JoinColumn( name="sport_center_id")},
            inverseJoinColumns = {@JoinColumn( name="sport_id")}
    )
    public List<Sport> getSports() {
        return sports;
    }

    public void setSports(List<Sport> sports) {
        this.sports = sports;
    }

    @OneToMany(
            mappedBy = "sportCenter",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    public List<Schedule> getCalendar() {
        return calendar;
    }

    public void setCalendar(List<Schedule> calendar) {
        this.calendar = calendar;
    }
}
