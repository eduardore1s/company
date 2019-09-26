package com.hotmart.api.company.repository;

import com.hotmart.api.company.model.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    List<Employee> findByProjectListDepartmentId(Long id);
    Optional<Employee> findByName(String name);

}
