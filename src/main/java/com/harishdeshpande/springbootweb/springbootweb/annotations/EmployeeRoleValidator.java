package com.harishdeshpande.springbootweb.springbootweb.annotations;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.List;
import java.util.Set;

public class EmployeeRoleValidator implements ConstraintValidator<EmployeeRoleValidation, String> {
    @Override
    public boolean isValid(String role, ConstraintValidatorContext constraintValidatorContext) {
        if(role == null) return false;
        Set<String> roles = Set.of("USER", "ADMIN");

        return roles.contains(role);
    }
}
