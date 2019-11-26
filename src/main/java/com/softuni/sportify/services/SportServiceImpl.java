package com.softuni.sportify.services;

import com.softuni.sportify.domain.entities.Image;
import com.softuni.sportify.domain.entities.Sport;
import com.softuni.sportify.domain.models.service_models.ImageServiceModel;
import com.softuni.sportify.domain.models.service_models.SportServiceModel;
import com.softuni.sportify.repositories.SportRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class SportServiceImpl implements SportService {

    private final ModelMapper modelMapper;
    private final SportRepository sportRepository;
    private final ImageService imageService;

    @Autowired
    public SportServiceImpl(ModelMapper modelMapper,
                            SportRepository sportRepository,
                            ImageService imageService) {
        this.modelMapper = modelMapper;
        this.sportRepository = sportRepository;
        this.imageService = imageService;
    }

    @Override
    public SportServiceModel addNewSport(SportServiceModel sportServiceModel) {
        Sport sport = this.modelMapper.map(sportServiceModel, Sport.class);

        this.sportRepository.saveAndFlush(sport);
        return  this.modelMapper.map(sport, SportServiceModel.class);
    }

    @Override
    public SportServiceModel findByID(String id) {
        Sport sport = this.sportRepository.findById(id).orElse(null);
        return this.modelMapper.map(sport, SportServiceModel.class);
    }

    @Override
    public SportServiceModel addSportImage(String id, ImageServiceModel imageServiceModel) {

        Image image = this.modelMapper.map(imageServiceModel, Image.class);
        Sport sport = this.sportRepository.findById(id).orElse(null);
        sport.getSportImages().add(image);

        sport.setSportImages(sport.getSportImages());

            this.sportRepository.saveAndFlush(sport);

        return this.modelMapper.map(sport, SportServiceModel.class);
    }

//    @Override
//    public void deleteImage(String sportID, String imageID) throws Exception {
//        Sport sport = this.sportRepository.findById(sportID).orElse(null);
//        SportServiceModel sportServiceModel = this.modelMapper.map(sport, SportServiceModel.class);
//        List<Image> updatedImagesSet = sportServiceModel.getImages()
//                .stream()
//                .filter(i -> !i.getId().equals(imageID))
//                .collect(Collectors.toList());
//        sport.setSportImages(updatedImagesSet);
//        this.sportRepository.saveAndFlush(sport);
//        this.imageService.deleteImage(imageID);
//    }
}
