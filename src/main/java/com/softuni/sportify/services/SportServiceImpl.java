package com.softuni.sportify.services;

import com.softuni.sportify.domain.entities.Sport;
import com.softuni.sportify.domain.models.service_models.SportServiceModel;
import com.softuni.sportify.repositories.SportRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedHashSet;

@Service
public class SportServiceImpl implements SportService {

    private final ModelMapper modelMapper;
    private final SportRepository sportRepository;

    @Autowired
    public SportServiceImpl(ModelMapper modelMapper,
                            SportRepository sportRepository) {
        this.modelMapper = modelMapper;
        this.sportRepository = sportRepository;
    }

    @Override
    public SportServiceModel addNewSport(SportServiceModel sportServiceModel) {
        Sport sport = this.modelMapper.map(sportServiceModel, Sport.class);
        sport.setImages(new LinkedHashSet<>());
        sport.setSportCenters(new LinkedHashSet<>());
        this.sportRepository.saveAndFlush(sport);
        return  this.modelMapper.map(sport, SportServiceModel.class);
    }

    @Override
    public SportServiceModel findByID(String id) {
        Sport sport = this.sportRepository.findById(id).orElse(null);
        return this.modelMapper.map(sport, SportServiceModel.class);
    }
}
