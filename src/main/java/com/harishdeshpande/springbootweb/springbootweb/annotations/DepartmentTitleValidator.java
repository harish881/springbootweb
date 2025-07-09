package com.harishdeshpande.springbootweb.springbootweb.annotations;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.Set;

public class DepartmentTitleValidator implements ConstraintValidator<DepartmentTitleValidation, String> {
    @Override
    public boolean isValid(String title, ConstraintValidatorContext constraintValidatorContext) {
        if(title == null) return false;
        Set<String> titles = Set.of("Finance", "Human Resource", "Engineering", "Sales", "Customer Success");

        return titles.contains(title);
    }
}
