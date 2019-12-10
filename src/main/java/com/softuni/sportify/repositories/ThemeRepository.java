package com.softuni.sportify.repositories;

import com.softuni.sportify.domain.entities.Theme;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ThemeRepository extends JpaRepository<Theme, String> {
}
