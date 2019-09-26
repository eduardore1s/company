package com.hotmart.api.company.services.business;

import com.hotmart.api.company.model.dto.request.EmployeeDtoRequest;
import com.hotmart.api.company.model.dto.response.EmployeeDtoResponse;

import java.util.List;

public interface EmployeeService {

    List<EmployeeDtoResponse> findAll();
    EmployeeDtoResponse create(EmployeeDtoRequest employeeDtoRequest);
    EmployeeDtoResponse update(Long id, EmployeeDtoRequest employeeDtoRequest);
    boolean delete(Long id);
    EmployeeDtoResponse findById(Long id);
    List<EmployeeDtoResponse> findByProjectListDepartmentId(Long idDepartment);
}
