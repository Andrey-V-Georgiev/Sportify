package com.softuni.sportify.repositories;

import com.softuni.sportify.domain.entities.Sport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface SportRepository extends JpaRepository<Sport, String> {

    Sport findByName(String name);
}
