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

        ThemeServiceModel themeServiceModel = null;
        try {
            themeServiceModel = this.themeService.findTheActiveTheme();
            String image1 = "";
            String image2 = "";
            String image3 = "";

            if (themeServiceModel.getIndexCarouselImages() == null ||
                    themeServiceModel.getIndexCarouselImages().size() < 1) {
                image1 = VIEW_OLYMPIC_LOGO;
            } else {
                image1 = themeServiceModel.getIndexCarouselImages().get(0).getImageURL();
            }

            if (themeServiceModel.getIndexCarouselImages() == null ||
                    themeServiceModel.getIndexCarouselImages().size() < 2) {
                image2 = VIEW_OLYMPIC_LOGO;
            } else {
                image2 = themeServiceModel.getIndexCarouselImages().get(1).getImageURL();
            }

            if (themeServiceModel.getIndexCarouselImages() == null ||
                    themeServiceModel.getIndexCarouselImages().size() < 3) {
                image3 = VIEW_OLYMPIC_LOGO;
            } else {
                image3 = themeServiceModel.getIndexCarouselImages().get(2).getImageURL();
            }
            modelAndView.addObject("image1", image1);
            modelAndView.addObject("image2", image2);
            modelAndView.addObject("image3", image3);
        } catch (Exception e) {
            modelAndView.addObject("image1", VIEW_OLYMPIC_LOGO);
            modelAndView.addObject("image2", VIEW_OLYMPIC_LOGO);
            modelAndView.addObject("image3", VIEW_OLYMPIC_LOGO);
        }

        modelAndView.setViewName(VIEW_INDEX);
        return modelAndView;
    }

    @GetMapping("/home")
    @PreAuthorize(IS_AUTHENTICATED)
    public ModelAndView home(ModelAndView modelAndView) {

        ThemeServiceModel themeServiceModel = null;
        try {
            themeServiceModel = this.themeService.findTheActiveTheme();
            String image1 = "";
            String image2 = "";
            String image3 = "";

            if (themeServiceModel.getHomeCarouselImages() == null ||
                    themeServiceModel.getHomeCarouselImages().size() < 1) {
                image1 = VIEW_OLYMPIC_LOGO;
            } else {
                image1 = themeServiceModel.getHomeCarouselImages().get(0).getImageURL();
            }

            if (themeServiceModel.getHomeCarouselImages() == null ||
                    themeServiceModel.getHomeCarouselImages().size() < 2) {
                image2 = VIEW_OLYMPIC_LOGO;
            } else {
                image2 = themeServiceModel.getHomeCarouselImages().get(1).getImageURL();
            }

            if (themeServiceModel.getHomeCarouselImages() == null ||
                    themeServiceModel.getHomeCarouselImages().size() < 3) {
                image3 = VIEW_OLYMPIC_LOGO;
            } else {
                image3 = themeServiceModel.getHomeCarouselImages().get(2).getImageURL();
            }
            modelAndView.addObject("image1", image1);
            modelAndView.addObject("image2", image2);
            modelAndView.addObject("image3", image3);
        } catch (Exception e) {
            modelAndView.addObject("image1", VIEW_OLYMPIC_LOGO);
            modelAndView.addObject("image2", VIEW_OLYMPIC_LOGO);
            modelAndView.addObject("image3", VIEW_OLYMPIC_LOGO);
        }
        modelAndView.setViewName(VIEW_HOME);
        return modelAndView;
    }

    @GetMapping("/admin-panel")
    @PreAuthorize(HAS_ROLE_ADMIN)
    public ModelAndView adminPanel(ModelAndView modelAndView) {

        ThemeServiceModel themeServiceModel = null;
        try {
            themeServiceModel = this.themeService.findTheActiveTheme();
            String usersImage = "";
            String imagesImage = "";
            String sportCentersImage = "";
            String sportsImage = "";

            if (themeServiceModel.getAdminPanelImages() == null ||
                    themeServiceModel.getAdminPanelImages().size() < 1) {
                usersImage = VIEW_OLYMPIC_LOGO;
            } else {
                usersImage = themeServiceModel.getAdminPanelImages().get(0).getImageURL();
            }

            if (themeServiceModel.getAdminPanelImages() == null ||
                    themeServiceModel.getAdminPanelImages().size() < 2) {
                imagesImage = VIEW_OLYMPIC_LOGO;
            } else {
                imagesImage = themeServiceModel.getAdminPanelImages().get(1).getImageURL();
            }

            if (themeServiceModel.getAdminPanelImages() == null ||
                    themeServiceModel.getAdminPanelImages().size() < 3) {
                sportCentersImage = VIEW_OLYMPIC_LOGO;
            } else {
                sportCentersImage = themeServiceModel.getAdminPanelImages().get(2).getImageURL();
            }

            if (themeServiceModel.getAdminPanelImages() == null ||
                    themeServiceModel.getAdminPanelImages().size() < 4) {
                sportsImage = VIEW_OLYMPIC_LOGO;
            } else {
                sportsImage = themeServiceModel.getAdminPanelImages().get(3).getImageURL();
            }
            modelAndView.addObject("usersImage", usersImage);
            modelAndView.addObject("imagesImage", imagesImage);
            modelAndView.addObject("sportCentersImage", sportCentersImage);
            modelAndView.addObject("sportsImage", sportsImage);

        } catch (Exception e) {
            modelAndView.addObject("usersImage", VIEW_OLYMPIC_LOGO);
            modelAndView.addObject("imagesImage", VIEW_OLYMPIC_LOGO);
            modelAndView.addObject("sportCentersImage", VIEW_OLYMPIC_LOGO);
            modelAndView.addObject("sportsImage", VIEW_OLYMPIC_LOGO);
        }
        modelAndView.setViewName(VIEW_ADMIN_PANEL);
        return modelAndView;
    }
}
