package com.hotmart.api.company.model.mapper;

import com.hotmart.api.company.model.form.DepartmentForm;
import com.hotmart.api.company.model.vo.DepartmentVo;
import com.hotmart.api.company.model.entity.Department;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface DepartmentMapper {

    Department toDepartment(DepartmentForm departmentForm);

    DepartmentVo toDepartmentVo(Department department);

    void updateDepartment(DepartmentForm departmentForm, @MappingTarget Department department);
}
