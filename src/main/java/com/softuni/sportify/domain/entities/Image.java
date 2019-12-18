package com.softuni.sportify.domain.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.Size;

@Entity
@Table(name ="images")
public class Image extends BaseEntity {

    private String name;
    private String imageURL;
    private String publicID;

    public Image() {
    }

    @Column(name = "name", nullable = false)
    @Size(min = 2, max = 30)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "image_url", nullable = false)
    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    @Column(name = "public_id", nullable = false)
    public String getPublicID() {
        return publicID;
    }

    public void setPublicID(String publicID) {
        this.publicID = publicID;
    }

}
