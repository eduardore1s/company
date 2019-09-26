package com.hotmart.api.company.services.data;

import com.hotmart.api.company.model.entity.Employee;

import java.util.List;
import java.util.Optional;

public interface EmployeeDataService {

    List<Employee> findAll();
    Employee save(Employee employee);
    void delete(Employee employee);
    Optional<Employee> findById(Long id);
    List<Employee> findByProjectListDepartmentId(Long idDepartment);
    Optional<Employee> findByName(String name);
}
