package com.softuni.sportify.web.controllers;

import com.softuni.sportify.domain.models.service_models.ThemeServiceModel;
import com.softuni.sportify.services.ThemeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import static com.softuni.sportify.constants.AuthConstants.*;
import static com.softuni.sportify.constants.HomeControllerConstants.*;

@Controller
public class HomeController {

    private final ThemeService themeService;

    @Autowired
    public HomeController(ThemeService themeService) {
        this.themeService = themeService;
    }

    @GetMapping("/")
    @PreAuthorize(IS_ANONYMOUS)
    public ModelAndView index(ModelAndView modelAndView) {

        ThemeServiceModel themeServiceModel = this.themeService.findTheActiveTheme();

        String image1 = "";
        String image2 = "";
        String image3 = "";
        if (themeServiceModel == null) {
            image1 = VIEW_OLYMPIC_LOGO;
            image2 = VIEW_OLYMPIC_LOGO;
            image3 = VIEW_OLYMPIC_LOGO;
        } else {
            image1 = themeServiceModel.getIndexCarouselImages().get(0).getImageURL();
            image2 = themeServiceModel.getIndexCarouselImages().get(1).getImageURL();
            image3 = themeServiceModel.getIndexCarouselImages().get(2).getImageURL();
        }
        modelAndView.addObject("image1", image1);
        modelAndView.addObject("image2", image2);
        modelAndView.addObject("image3", image3);

        modelAndView.setViewName(VIEW_INDEX);

        return modelAndView;
    }

    @GetMapping("/home")
    @PreAuthorize(IS_AUTHENTICATED)
    public ModelAndView home(ModelAndView modelAndView) {

        ThemeServiceModel themeServiceModel = this.themeService.findTheActiveTheme();
        String image1 = "";
        String image2 = "";
        String image3 = "";
        if (themeServiceModel == null) {
            image1 = VIEW_OLYMPIC_LOGO;
            image2 = VIEW_OLYMPIC_LOGO;
            image3 = VIEW_OLYMPIC_LOGO;
        } else {
            image1 = themeServiceModel.getHomeCarouselImages().get(0).getImageURL();
            image2 = themeServiceModel.getHomeCarouselImages().get(1).getImageURL();
            image3 = themeServiceModel.getHomeCarouselImages().get(2).getImageURL();
        }

        modelAndView.addObject("image1", image1);
        modelAndView.addObject("image2", image2);
        modelAndView.addObject("image3", image3);

        modelAndView.setViewName(VIEW_HOME);
        return modelAndView;
    }

    @GetMapping("/admin-panel")
    @PreAuthorize(HAS_ROLE_ADMIN)
    public ModelAndView adminPanel(ModelAndView modelAndView) {

        ThemeServiceModel themeServiceModel = this.themeService.findTheActiveTheme();
        String usersImage = "";
        String imagesImage = "";
        String sportCentersImage = "";
        String sportsImage = "";
        if (themeServiceModel == null) {
            usersImage = VIEW_OLYMPIC_LOGO;
            imagesImage = VIEW_OLYMPIC_LOGO;
            sportCentersImage = VIEW_OLYMPIC_LOGO;
            sportsImage = VIEW_OLYMPIC_LOGO;
        } else {
            usersImage = themeServiceModel.getAdminPanelImages().get(0).getImageURL();
            imagesImage = themeServiceModel.getAdminPanelImages().get(1).getImageURL();
            sportCentersImage = themeServiceModel.getAdminPanelImages().get(2).getImageURL();
            sportsImage = themeServiceModel.getAdminPanelImages().get(3).getImageURL();
        }

        modelAndView.addObject("usersImage", usersImage);
        modelAndView.addObject("imagesImage", imagesImage);
        modelAndView.addObject("sportCentersImage", sportCentersImage);
        modelAndView.addObject("sportsImage", sportsImage);

        modelAndView.setViewName(VIEW_ADMIN_PANEL);
        return modelAndView;
    }
}
