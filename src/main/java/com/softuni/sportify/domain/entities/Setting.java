package com.softuni.sportify.domain.entities;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "settings")
public class Setting extends BaseEntity {

    private String name;
    private List<Image> indexCarouselImages;
    private List<Image> homeCarouselImages;
    private List<Image> images;

    public Setting() {
    }

    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }



    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "index_carousel_images",
            joinColumns = {@JoinColumn(name = "index_carousel_id")},
            inverseJoinColumns = {@JoinColumn(name = "image_id")}
    )
    public List<Image> getIndexCarouselImages() {
        return indexCarouselImages;
    }

    public void setIndexCarouselImages(List<Image> indexCarouselImages) {
        this.indexCarouselImages = indexCarouselImages;
    }

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "home_carousel_images",
            joinColumns = {@JoinColumn(name = "home_carousel_id")},
            inverseJoinColumns = {@JoinColumn(name = "image_id")}
    )
    public List<Image> getHomeCarouselImages() {
        return homeCarouselImages;
    }

    public void setHomeCarouselImages(List<Image> homeCarouselImages) {
        this.homeCarouselImages = homeCarouselImages;
    }

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "settings_images",
            joinColumns = {@JoinColumn(name = "settings_id")},
            inverseJoinColumns = {@JoinColumn(name = "image_id")}
    )
    public List<Image> getImages() {
        return images;
    }

    public void setImages(List<Image> images) {
        this.images = images;
    }
}
