package com.softuni.sportify.validation;

import com.softuni.sportify.validation.ImageMultipartFileConstraints;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ImageMultipartFileValidator implements ConstraintValidator<ImageMultipartFileConstraints, MultipartFile> {
    @Override
    public boolean isValid(MultipartFile multipartFile, ConstraintValidatorContext constraintValidatorContext) {

        double sizeInMB = multipartFile.getSize() * 0.00000095367432;
        return (sizeInMB < 1.0 && sizeInMB > 0.0);
    }
}
