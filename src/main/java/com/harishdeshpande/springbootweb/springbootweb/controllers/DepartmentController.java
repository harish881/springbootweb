package com.harishdeshpande.springbootweb.springbootweb.controllers;

import com.harishdeshpande.springbootweb.springbootweb.dto.DepartmentDTO;
import com.harishdeshpande.springbootweb.springbootweb.dto.EmployeeDTO;
import com.harishdeshpande.springbootweb.springbootweb.exceptions.ResourceNotFoundException;
import com.harishdeshpande.springbootweb.springbootweb.services.DepartmentService;
import com.harishdeshpande.springbootweb.springbootweb.services.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path="/departments")
public class DepartmentController {

    private final DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @GetMapping
    public ResponseEntity<List<DepartmentDTO>> getAllDepartments(@RequestParam(required = false) Integer age, @RequestParam(required = false) String sortBy){

        return ResponseEntity.ok(departmentService.getAllDepartments());
    }

    @PostMapping
    public ResponseEntity<DepartmentDTO> createEmployee(@RequestBody @Valid DepartmentDTO inputDepartment) throws Exception{
        DepartmentDTO savedEmployee =  departmentService.createNewDepartment(inputDepartment);
        return new ResponseEntity<>(savedEmployee, HttpStatus.CREATED);
    }

    @GetMapping(path="/{departmentId}")
    public ResponseEntity<DepartmentDTO> getDepartmentById(@PathVariable Long departmentId) {
        Optional<DepartmentDTO> departmentDTO = departmentService.getDepartmentById(departmentId);
        return departmentDTO.map(departmentDTO1 ->ResponseEntity.ok(departmentDTO1)).orElseThrow(() -> new ResourceNotFoundException("Department Not found with id " + departmentId));

    }

    @PutMapping(path = "/{departmentId}")
    public ResponseEntity<DepartmentDTO> updateEmployeeById(@RequestBody @Valid DepartmentDTO inputDepartment, @PathVariable Long departmentId){

        return ResponseEntity.ok(departmentService.updateEmployeeById(departmentId, inputDepartment));
    }

    @DeleteMapping(path = "/{departmentId}")
    public ResponseEntity<Boolean> deleteDepartmentById(@PathVariable Long departmentId) {
        boolean gotDeleted = departmentService.deleteDepartmentId(departmentId);
        if(gotDeleted) return ResponseEntity.ok(true);
        return ResponseEntity.notFound().build();
    }
}
