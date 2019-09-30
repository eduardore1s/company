package com.hotmart.api.company.repository;

import com.hotmart.api.company.model.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepository extends JpaRepository<Department, Long> {
}

