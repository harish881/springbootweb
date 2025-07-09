package com.harishdeshpande.springbootweb.springbootweb.services;

import com.harishdeshpande.springbootweb.springbootweb.dto.DepartmentDTO;
import com.harishdeshpande.springbootweb.springbootweb.dto.EmployeeDTO;
import com.harishdeshpande.springbootweb.springbootweb.entities.DepartmentEntity;
import com.harishdeshpande.springbootweb.springbootweb.entities.EmployeeEntity;
import com.harishdeshpande.springbootweb.springbootweb.exceptions.ResourceNotFoundException;
import com.harishdeshpande.springbootweb.springbootweb.repositories.DepartmentRepository;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DepartmentService {

    private final ModelMapper modelMapper;
    private final DepartmentRepository departmentRepository;

    public DepartmentService(DepartmentRepository departmentRepository, ModelMapper modelMapper){
        this.departmentRepository = departmentRepository;
        this.modelMapper = modelMapper;
    }

    public DepartmentDTO createNewDepartment(DepartmentDTO departmentDto) throws Exception {
        String title = departmentDto.getTitle();
        boolean deptExists = existsDepartmentByTitle(departmentDto.getTitle());
        if(deptExists) throw new Exception("Department already exists with title " + title);
        DepartmentEntity toSaveEntity = modelMapper.map(departmentDto, DepartmentEntity.class);
        DepartmentEntity savedEmployeeEntity =  departmentRepository.save(toSaveEntity);
        return modelMapper.map(savedEmployeeEntity, DepartmentDTO.class);
    }

    public List<DepartmentDTO> getAllDepartments(){
        List<DepartmentEntity> departmentEntities =  departmentRepository.findAll();

        return departmentEntities
                .stream()
                .map(departmentEntity -> modelMapper.map(departmentEntity, DepartmentDTO.class))
                .collect(Collectors.toList());
    }

    public Optional<DepartmentDTO> getDepartmentById(Long departmentId) {
        return departmentRepository.findById(departmentId).map(departmentEntity -> modelMapper.map(departmentEntity, DepartmentDTO.class));
    }

    public DepartmentDTO updateEmployeeById(Long departmentId, @Valid DepartmentDTO inputDepartment) throws ResourceNotFoundException {
        isExistsByDepartmentId(departmentId);
        DepartmentEntity toSaveEntity = modelMapper.map(inputDepartment, DepartmentEntity.class);
        toSaveEntity.setId(departmentId);
        DepartmentEntity savedDepartmentEntity = departmentRepository.save(toSaveEntity);
        return modelMapper.map(savedDepartmentEntity, DepartmentDTO.class);
    }

    public Optional<DepartmentDTO> getEmployeeById(Long departmentId) {

        //Optional<EmployeeEntity> empEntity =  employeeRepository.findById(employeeId);
        //return modelMapper.map(empEntity1 ->, modelMapper.map(empEntity1, EmployeeDTO.class));

        return departmentRepository.findById(departmentId).map(departmentEntity -> modelMapper.map(departmentEntity, DepartmentDTO.class));
    }

    private boolean existsDepartmentByTitle(String title){
        return departmentRepository.existsDepartmentByTitleIgnoreCase(title);
    }

    private boolean isExistsByDepartmentId(Long departmentId){
        boolean deptExists = departmentRepository.existsById(departmentId);
        if(!deptExists) throw new ResourceNotFoundException("Department doesn't exists with id " + departmentId);
        return true;
    }

    public boolean deleteDepartmentId(Long departmentId) throws ResourceNotFoundException {

        isExistsByDepartmentId(departmentId);
        departmentRepository.deleteById(departmentId);
        return true;
    }
}
