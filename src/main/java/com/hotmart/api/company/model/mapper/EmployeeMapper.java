package com.hotmart.api.company.model.mapper;

import com.hotmart.api.company.model.form.EmployeeForm;
import com.hotmart.api.company.model.vo.EmployeeVo;
import com.hotmart.api.company.model.entity.Employee;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface EmployeeMapper {

    Employee toEmployee(EmployeeForm employeeForm);

    EmployeeVo toEmployeeDtoResponse(Employee employee);

    void updateEmployee(EmployeeForm employeeForm, @MappingTarget Employee employee);
}
