package com.softuni.sportify.services;

import com.softuni.sportify.domain.entities.Image;
import com.softuni.sportify.domain.entities.Sport;
import com.softuni.sportify.domain.models.service_models.ImageServiceModel;
import com.softuni.sportify.domain.models.service_models.SportServiceModel;
import com.softuni.sportify.repositories.SportRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public SportServiceModel createSport(SportServiceModel sportServiceModel,
                                         ImageServiceModel descriptionImageServiceModel,
                                         ImageServiceModel iconImageServiceModel) {

        Sport sport = this.modelMapper.map(sportServiceModel, Sport.class);
        sport.setSportDescription("");
        sport.setDescriptionImage(this.modelMapper.map(descriptionImageServiceModel, Image.class));
        sport.setIconImage(this.modelMapper.map(iconImageServiceModel, Image.class));

        Sport newSport = null;
        try {
            newSport = this.sportRepository.saveAndFlush(sport);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return  this.modelMapper.map(newSport, SportServiceModel.class);
    }

    @Override
    public SportServiceModel findByID(String id) {
        Sport sport = this.sportRepository.findById(id).orElse(null);
        return this.modelMapper.map(sport, SportServiceModel.class);
    }



    @Override
    public SportServiceModel updateSportDescription(SportServiceModel sportServiceModel) {
        Sport sport = this.modelMapper.map(sportServiceModel, Sport.class);
        Sport updatedSport = this.sportRepository.saveAndFlush(sport);
        return this.modelMapper.map(updatedSport, SportServiceModel.class);
    }

    @Override
    public SportServiceModel addSportImage(SportServiceModel sportServiceModel,
                                           ImageServiceModel imageServiceModel) {

        Sport sport = this.modelMapper.map(sportServiceModel, Sport.class);
        Image image = this.modelMapper.map(imageServiceModel, Image.class);
        sport.getSportImages().add(image);
        Sport updatedSport = this.sportRepository.saveAndFlush(sport);
        return this.modelMapper.map(updatedSport, SportServiceModel.class);
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
