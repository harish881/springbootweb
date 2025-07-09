package com.harishdeshpande.springbootweb.springbootweb.annotations;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.Set;

public class PrimeNumberValidator implements ConstraintValidator<PrimeNumberValidation, Long> {

    @Override
    public boolean isValid(Long digit, ConstraintValidatorContext constraintValidatorContext) {
        if(digit <= 1) return false;

        for(int  i = 2; i*i <= digit; i++){
            if(digit % i == 0){
                return false;
            }

        }

        return true;
    }
}
