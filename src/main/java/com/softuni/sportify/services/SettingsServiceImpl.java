package com.softuni.sportify.services;

import com.softuni.sportify.domain.entities.Settings;
import com.softuni.sportify.repositories.SettingsRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SettingsServiceImpl implements SettingsService {

    private final SettingsRepository settingsRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public SettingsServiceImpl(SettingsRepository settingsRepository,
                               ModelMapper modelMapper) {
        this.settingsRepository = settingsRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<Settings> findAll() {
       return this.settingsRepository.findAll();
    }
}
