package com.harishdeshpande.springbootweb.springbootweb.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.harishdeshpande.springbootweb.springbootweb.annotations.DepartmentTitleValidation;
import com.harishdeshpande.springbootweb.springbootweb.annotations.PrimeNumberValidation;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DepartmentDTO {

    private Long id;
    @DepartmentTitleValidation
    private String title;
    @JsonProperty("isActive")
    private Boolean isActive;
    private LocalDate createdAt;
    @PrimeNumberValidation
    private Long digit;

}
