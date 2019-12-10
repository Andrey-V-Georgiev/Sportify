package com.softuni.sportify.services;

import com.softuni.sportify.domain.entities.Image;
import com.softuni.sportify.domain.entities.Sport;
import com.softuni.sportify.domain.models.service_models.ImageServiceModel;
import com.softuni.sportify.domain.models.service_models.SportServiceModel;
import com.softuni.sportify.repositories.ImageRepository;
import com.softuni.sportify.repositories.SportRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SportServiceImpl implements SportService {

    private final ModelMapper modelMapper;
    private final SportRepository sportRepository;
    private final ImageRepository imageRepository;
    private final SportCenterService sportCenterService;
    private final EventService eventService;
    private final ScheduleService scheduleService;

    @Autowired
    public SportServiceImpl(ModelMapper modelMapper,
                            SportRepository sportRepository,
                            ImageRepository imageRepository,
                            SportCenterService sportCenterService,
                            EventService eventService,
                            ScheduleService scheduleService) {
        this.modelMapper = modelMapper;
        this.sportRepository = sportRepository;
        this.imageRepository = imageRepository;
        this.sportCenterService = sportCenterService;
        this.eventService = eventService;
        this.scheduleService = scheduleService;
    }

    @Override
    public SportServiceModel createSport(SportServiceModel sportServiceModel,
                                         ImageServiceModel iconImageServiceModel) {

        Sport sport = this.modelMapper.map(sportServiceModel, Sport.class);
        sport.setSportDescription("");
        sport.setIconImage(this.modelMapper.map(iconImageServiceModel, Image.class));

        Sport newSport = null;
        try {
            newSport = this.sportRepository.saveAndFlush(sport);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return this.modelMapper.map(newSport, SportServiceModel.class);
    }

    @Override
    public SportServiceModel findByID(String id) {

        Sport sport = this.sportRepository.findById(id).orElse(null);
        return this.modelMapper.map(sport, SportServiceModel.class);
    }

    @Override
    public SportServiceModel findByName(String name) {

        Sport sport = this.sportRepository.findByName(name);
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

    @Override
    public List<SportServiceModel> findAllSports() {

        return this.sportRepository.findAll()
                .stream()
                .map(s -> this.modelMapper.map(s, SportServiceModel.class))
                .collect(Collectors.toList());
    }

    @Override
    public void deleteSportImage(String sportID, String imageID) {

        Sport sport = this.sportRepository.findById(sportID).orElse(null);
        Image image = this.imageRepository.findById(imageID).orElse(null);

        List<Image> sportImages = sport.getSportImages()
                .stream()
                .filter(i -> !i.getId().equals(image.getId()))
                .collect(Collectors.toList());
        sport.setSportImages(sportImages);

        this.sportRepository.save(sport);
    }

    @Override
    public SportServiceModel editIconImage(SportServiceModel sportServiceModel) {

        Sport sport = this.sportRepository.findById(sportServiceModel.getId()).orElse(null);
        sport.getIconImage().setName(sportServiceModel.getIconImage().getName());
        return this.modelMapper.map(this.sportRepository.saveAndFlush(sport), SportServiceModel.class);
    }

    @Override
    public List<String> findAllSportsNames() {

        return this.sportRepository.findAll()
                .stream()
                .map(Sport::getName)
                .collect(Collectors.toList());
    }

    @Override
    public List<SportServiceModel> findAllSportsStartsWith(SportServiceModel sportServiceModel) {

        List<SportServiceModel> allSportServiceModels = this.findAllSports();
        Comparator<SportServiceModel> startsWithSport = Comparator
                .comparing(s -> !s.getId().equals(sportServiceModel.getId()));
        List<SportServiceModel> sortedSports = allSportServiceModels
                .stream()
                .sorted(startsWithSport)
                .collect(Collectors.toList());

        return sortedSports;
    }

    @Override
    public List<String> findAllSportsNamesStartsWith(SportServiceModel sportServiceModel) {

        List<SportServiceModel> allSportServiceModels = this.findAllSports();
        Comparator<SportServiceModel> startsWithSport = Comparator
                .comparing(s -> !s.getId().equals(sportServiceModel.getId()));
        List<String> sortedSportsNames = allSportServiceModels
                .stream()
                .sorted(startsWithSport)
                .map(s -> s.getName())
                .collect(Collectors.toList());

        return sortedSportsNames;
    }

    @Override
    public void deleteSport(SportServiceModel sportServiceModel) {
        /* remove current sport from sport centers structures */
        this.sportCenterService.removeCurrentSport(sportServiceModel);
        /* delete all events of this sport */
        this.eventService.deleteAllBySport(sportServiceModel);
        /* empty images list */
        sportServiceModel.setSportImages(new ArrayList<>());

        this.sportRepository.delete(this.modelMapper.map(sportServiceModel, Sport.class));
    }
}
