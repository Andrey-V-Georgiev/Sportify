package com.softuni.sportify.domain.entities;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "sports")
public class Sport extends BaseEntity {

    private String name;
    private String sportDescription;
    private Image iconImage;
    private List<Image> sportImages;

    public Sport() {
    }

    @Column(name = "name", nullable = false)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "sport_description", nullable = false, columnDefinition = "TEXT")
    public String getSportDescription() {
        return sportDescription;
    }

    public void setSportDescription(String sportDescription) {
        this.sportDescription = sportDescription;
    }

    @ManyToOne(optional = false)
    @JoinColumn(name="sport_icon_image_id")
    public Image getIconImage() {
        return iconImage;
    }

    public void setIconImage(Image iconImage) {
        this.iconImage = iconImage;
    }

    @ManyToMany(targetEntity = Image.class, fetch = FetchType.EAGER)
    @JoinTable(
            name="sport_images",
            joinColumns = {@JoinColumn( name="sport_id")},
            inverseJoinColumns = {@JoinColumn( name="image_id")}
    )
    public List<Image> getSportImages() {
        return sportImages;
    }

    public void setSportImages(List<Image> sportImages) {
        this.sportImages = sportImages;
    }

}
