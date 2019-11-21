package com.softuni.sportify.services;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public interface CloudinaryService {

    String uploadImage(MultipartFile multipartFile) throws IOException;

    void deleteImage(String publicID) throws Exception;
}
