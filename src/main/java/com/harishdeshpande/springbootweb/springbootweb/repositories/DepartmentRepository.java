package com.harishdeshpande.springbootweb.springbootweb.repositories;

import com.harishdeshpande.springbootweb.springbootweb.entities.DepartmentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepository extends JpaRepository<DepartmentEntity, Long> {
    boolean existsDepartmentByTitleIgnoreCase(String title);
}

