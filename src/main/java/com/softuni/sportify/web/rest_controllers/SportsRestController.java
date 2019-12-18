package com.softuni.sportify.web.rest_controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.softuni.sportify.domain.models.view_models.ImageViewModel;
import com.softuni.sportify.exceptions.ReadException;
import com.softuni.sportify.services.ImageService;
import com.softuni.sportify.services.SportService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/rest/sports")
public class SportsRestController {

    private final ImageService imageService;
    private final ModelMapper modelMapper;
    private final ObjectMapper objectMapper;

    @Autowired
    public SportsRestController(ImageService imageService,
                                ModelMapper modelMapper,
                                ObjectMapper objectMapper) {
        this.imageService = imageService;
        this.modelMapper = modelMapper;
        this.objectMapper = objectMapper;
    }

    @GetMapping("/load-icon-image/{id}")
    public String loadIconImage(@PathVariable String id) throws JsonProcessingException, ReadException {

        ImageViewModel imageViewModel = this.modelMapper
                .map(this.imageService.findImageByID(id), ImageViewModel.class);
        return this.objectMapper.writeValueAsString(imageViewModel);
    }

}
