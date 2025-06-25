package com.harishdeshpande.springbootweb.springbootweb.annotations;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import org.springframework.web.bind.annotation.RequestBody;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Constraint(validatedBy = {EmployeeRoleValidator.class})
public @interface EmployeeRoleValidation {

    String message() default "role can only be ADMIN OR USER";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
