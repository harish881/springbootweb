package com.harishdeshpande.springbootweb.springbootweb.annotations;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Constraint(validatedBy = {DepartmentTitleValidator.class})
public @interface DepartmentTitleValidation {
    String message() default "Title can only be Finance, Human Resource, Engineering, Sales or Customer Success";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
