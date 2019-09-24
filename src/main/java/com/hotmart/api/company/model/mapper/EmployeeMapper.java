package com.hotmart.api.company.model.mapper;

import com.hotmart.api.company.model.dto.request.EmployeeDtoRequest;
import com.hotmart.api.company.model.dto.response.EmployeeDtoResponse;
import com.hotmart.api.company.model.entity.Employee;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface EmployeeMapper {

    Employee toEmployee(EmployeeDtoRequest employeeDtoRequest);

    EmployeeDtoResponse toEmployeeDtoResponse(Employee employee);

    void updateEmployee(EmployeeDtoRequest employeeDtoRequest, @MappingTarget Employee employee);
}
