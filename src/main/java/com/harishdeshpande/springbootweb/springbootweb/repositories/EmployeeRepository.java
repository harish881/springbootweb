package com.harishdeshpande.springbootweb.springbootweb.repositories;

import com.harishdeshpande.springbootweb.springbootweb.entities.EmployeeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<EmployeeEntity, Long> {
}
