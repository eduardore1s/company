package com.hotmart.api.company.services.data;

import com.hotmart.api.company.model.entity.Department;

import java.util.List;
import java.util.Optional;

public interface DepartmentDataService {

    List<Department> findAll();
    Department save(Department department);
    void delete(Department department);
    Optional<Department> findById(Long id);

}
