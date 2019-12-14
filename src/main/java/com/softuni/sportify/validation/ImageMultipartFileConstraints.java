package com.softuni.sportify.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.FIELD })
@Constraint(validatedBy = ImageMultipartFileValidator.class)
public @interface ImageMultipartFileConstraints {
    String message() default "Image field is required and uploaded file must be up to 1.0MB!";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
