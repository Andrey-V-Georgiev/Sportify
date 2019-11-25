package com.softuni.sportify.services;

import com.softuni.sportify.domain.entities.Settings;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public interface SettingsService {

    List<Settings> findAll();
}


