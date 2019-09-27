package com.hotmart.api.company.model.mapper;

import com.hotmart.api.company.controller.form.EmployeeForm;
import com.hotmart.api.company.controller.vo.EmployeeVo;
import com.hotmart.api.company.model.entity.Employee;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface EmployeeMapper {

    Employee toEmployee(EmployeeForm employeeForm);

    EmployeeVo toEmployeeVo(Employee employee);

    void updateEmployee(EmployeeForm employeeForm, @MappingTarget Employee employee);
}
