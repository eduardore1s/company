package com.hotmart.api.company.data;

import com.hotmart.api.company.controller.form.ProjectForm;
import com.hotmart.api.company.model.entity.Employee;
import com.hotmart.api.company.model.entity.Project;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ProjectDataFactory {

    public static Project buildProject(Long id){

        final List<Employee> employees = new ArrayList<>();
        employees.add(EmployeeDataFactory.buildEmployee(1L));

        final Project project = new Project();
        project.setDepartment(DepartmentDataFactory.buildDepartment(1L));
        project.setName("NAME OF " + id.toString());
        project.setDateStart(LocalDate.parse("2019-10-01"));
        project.setDateFinal(LocalDate.parse("2019-12-31"));
        project.setValue(new BigDecimal(3500));
        project.setEmployeeList(employees);

        return project;
    }


    public static ProjectForm buildProjectDtoRequest(Long id){

        final List<Long> employees = new ArrayList<>();
        employees.add(1L);

        final ProjectForm project = new ProjectForm();
        project.setIdDepartment(1L);
        project.setDateStart(LocalDate.parse("2019-10-01"));
        project.setDateFinal(LocalDate.parse("2019-12-31"));
        project.setName("NAME OF " + id.toString());
        project.setValue(new BigDecimal(3500));
//        project.setIdEmployees(employees); TODO

        return project;
    }

}
