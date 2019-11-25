package com.softuni.sportify.domain.entities;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "settings")
public class Settings extends BaseEntity {

    private List<Image> images;

    public Settings() {
    }

    @ManyToMany(targetEntity = Image.class, fetch = FetchType.EAGER)
    @JoinTable(
            name="settings_images",
            joinColumns = {@JoinColumn( name="settings_id")},
            inverseJoinColumns = {@JoinColumn( name="image_id")}
    )
    public List<Image> getImages() {
        return images;
    }

    public void setImages(List<Image> images) {
        this.images = images;
    }
}
