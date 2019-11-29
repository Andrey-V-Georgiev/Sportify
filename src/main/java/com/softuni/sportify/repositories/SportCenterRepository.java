package com.softuni.sportify.repositories;

import com.softuni.sportify.domain.entities.SportCenter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SportCenterRepository extends JpaRepository<SportCenter, String> {

}
