package com.softuni.sportify.repositories;

import com.softuni.sportify.domain.entities.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ImageRepository extends JpaRepository<Image, String> {

    Optional<Image> findByName(String name);

    Optional<Image> findByImageURL(String imageURL);
}