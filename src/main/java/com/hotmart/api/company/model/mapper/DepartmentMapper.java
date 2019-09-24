package com.hotmart.api.company.model.mapper;

import com.hotmart.api.company.model.dto.request.DepartmentDtoRequest;
import com.hotmart.api.company.model.dto.response.DepartmentDtoResponse;
import com.hotmart.api.company.model.entity.Department;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface DepartmentMapper {

    Department toDepartment(DepartmentDtoRequest departmentDtoRequest);

    DepartmentDtoResponse toDepartmentDtoResponse(Department department);

    void updateDepartment(DepartmentDtoRequest departmentDtoRequest, @MappingTarget Department department);
}
