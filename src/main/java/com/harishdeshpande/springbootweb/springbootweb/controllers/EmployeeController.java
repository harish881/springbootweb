package com.harishdeshpande.springbootweb.springbootweb.controllers;

import com.harishdeshpande.springbootweb.springbootweb.dto.EmployeeDTO;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping(path="/employees")
public class EmployeeController {

//    @GetMapping(path="/getSecretMessage")
//    public String getMySuperSecretMessage(){
//        return "Secret message: aadkjaslj";
//    }

    @GetMapping(path="/{employeeId}")
    public EmployeeDTO getEmployeeById(@PathVariable Long employeeId){

        return new EmployeeDTO(employeeId, "Harish", "harish@abc.com", 27, LocalDate.of(2024, 1,2), true);
    }

    @GetMapping
    public String getEmployees(@RequestParam(required = false) Integer age, @RequestParam(required = false) String sortBy){

        return "Hi age " + age + " sortBy " + sortBy;
    }

    @PostMapping
    public EmployeeDTO createEmployee(@RequestBody EmployeeDTO inputEmployee){
        inputEmployee.setId(100L);
        return inputEmployee;
    }
}
