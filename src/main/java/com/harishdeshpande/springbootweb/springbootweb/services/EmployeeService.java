package com.harishdeshpande.springbootweb.springbootweb.services;

import com.harishdeshpande.springbootweb.springbootweb.dto.EmployeeDTO;
import com.harishdeshpande.springbootweb.springbootweb.entities.EmployeeEntity;
import com.harishdeshpande.springbootweb.springbootweb.exceptions.ResourceNotFoundException;
import com.harishdeshpande.springbootweb.springbootweb.repositories.EmployeeRepository;
import org.modelmapper.ModelMapper;
import org.springframework.data.util.ReflectionUtils;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final ModelMapper modelMapper;

    public EmployeeService(EmployeeRepository employeeRepository, ModelMapper modelMapper){
        this.employeeRepository = employeeRepository;
        this.modelMapper = modelMapper;
    }

    public Optional<EmployeeDTO> getEmployeeById(Long employeeId) {

        //Optional<EmployeeEntity> empEntity =  employeeRepository.findById(employeeId);
        //return modelMapper.map(empEntity1 ->, modelMapper.map(empEntity1, EmployeeDTO.class));

        return employeeRepository.findById(employeeId).map(employeeEntity -> modelMapper.map(employeeEntity, EmployeeDTO.class));
    }

    public List<EmployeeDTO> getAllEmployees(){
        List<EmployeeEntity> employeeEntities =  employeeRepository.findAll();

        return employeeEntities
                .stream()
                .map(employeeEntity -> modelMapper.map(employeeEntity, EmployeeDTO.class))
                .collect(Collectors.toList());
    }

    public EmployeeDTO createNewEmployee(EmployeeDTO employeeDto){
        EmployeeEntity toSaveEntity = modelMapper.map(employeeDto, EmployeeEntity.class);
        EmployeeEntity savedEmployeeEntity =  employeeRepository.save(toSaveEntity);
        return modelMapper.map(savedEmployeeEntity, EmployeeDTO.class);
    }

    public EmployeeDTO updateEmployeeById(Long id, EmployeeDTO inputEmployee) {
        isExistsByEmployeeId(id);
        EmployeeEntity toSaveEntity = modelMapper.map(inputEmployee, EmployeeEntity.class);
        toSaveEntity.setId(id);
        EmployeeEntity savedEmployeeEntity = employeeRepository.save(toSaveEntity);
        return modelMapper.map(savedEmployeeEntity, EmployeeDTO.class);

    }

    private boolean isExistsByEmployeeId(Long employeeId){
        boolean exists = employeeRepository.existsById(employeeId);
        if(!exists) throw new ResourceNotFoundException("Employee Not found with id " + employeeId);
        return true;
    }

    public boolean deleteEmployeeId(Long employeeId) {
            isExistsByEmployeeId(employeeId);

            employeeRepository.deleteById(employeeId);
            return true;

    }

    public EmployeeDTO updatePartialEmployeeById(Map<String, Object> updates, Long employeeId) {
        isExistsByEmployeeId(employeeId);
        EmployeeEntity employeeEntity = employeeRepository.findById(employeeId).get();
        updates.forEach((field, value) -> {
            Field fieldToBeUpdated = ReflectionUtils.getRequiredField(EmployeeEntity.class, field);
            fieldToBeUpdated.setAccessible(true);
            ReflectionUtils.setField(fieldToBeUpdated, employeeEntity, value);
        });

        employeeRepository.save(employeeEntity);
        return modelMapper.map(employeeRepository.save(employeeEntity), EmployeeDTO.class);
    }
}
