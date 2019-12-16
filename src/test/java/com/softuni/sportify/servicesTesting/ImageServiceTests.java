//package com.softuni.sportify.servicesTesting;
//
//
//import com.softuni.sportify.exceptions.CreateException;
//import com.softuni.sportify.repositories.ImageRepository;
//import com.softuni.sportify.services.ImageService;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.test.context.junit4.SpringRunner;
//
//@SpringBootTest
//@RunWith(SpringRunner.class)
//public class ImageServiceTests {
//
//    @Autowired
//    private ImageService imageService;
//
//    @MockBean
//    private ImageRepository mockImageRepository;
//
//
//    @Test(expected = Exception.class)
//    public void imageService_createImageNullModel_throwsException() throws CreateException {
//
//        this.imageService.createImage(null, "Testing");
//    }
//}
