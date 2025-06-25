package com.harishdeshpande.springbootweb.springbootweb.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.harishdeshpande.springbootweb.springbootweb.annotations.EmployeeRoleValidation;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeDTO {

    private Long id;
    @NotBlank(message = "Name of the employee cannot be blank")
    @Size(min = 3, max = 10, message ="Character should be bet 3-10" )
    private String name;
    @Email(message = "Email should be valid")
    private String email;
    @Min(value=18, message="Age cannot be less than 18")
    @Max(value=80, message="Age cannot be greater than 80")
    @Positive
    private Integer age;
    private LocalDate dateOfJoining;
    @JsonProperty("isActive")
    private Boolean isActive;
    //@Pattern(regexp = "^(ADMIN|USER)$", message="role can only be ADMIN OR USER")
    @EmployeeRoleValidation
    private String role; //ADMIN, USER

}
