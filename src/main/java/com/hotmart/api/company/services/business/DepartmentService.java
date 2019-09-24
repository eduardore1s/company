package com.hotmart.api.company.services.business;

import com.hotmart.api.company.model.dto.request.DepartmentDtoRequest;
import com.hotmart.api.company.model.dto.response.DepartmentDtoResponse;

import java.util.List;

public interface DepartmentService {

    List<DepartmentDtoResponse> findAll();
    DepartmentDtoResponse create(DepartmentDtoRequest departmentDtoRequest);
    DepartmentDtoResponse update(Long id, DepartmentDtoRequest departmentDtoRequest);
    boolean delete(Long id);
    DepartmentDtoResponse findById(Long id);
}
