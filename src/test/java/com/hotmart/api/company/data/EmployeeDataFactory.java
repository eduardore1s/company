package com.hotmart.api.company.data;

import com.hotmart.api.company.model.form.EmployeeForm;
import com.hotmart.api.company.model.entity.Employee;
import com.hotmart.api.company.model.entity.EmployeeGender;

import java.math.BigDecimal;
import java.time.LocalDate;

public class EmployeeDataFactory {

    public static Employee buildEmployee(Long id){

        final Employee supervisor = new Employee();
        supervisor.setId(id + 1000);
        supervisor.setSupervisor(null);
        supervisor.setAddress(AddressDataFactory.buildAddress(1L));
        supervisor.setGender(EmployeeGender.MALE);
        supervisor.setCpf("CPF OF " + id.toString());
        supervisor.setDateOfBirth(LocalDate.parse("1990-10-06"));
        supervisor.setName("NAME OF " + id.toString());
        supervisor.setSalary(new BigDecimal(2500));
//        supervisor.setProjectList(); TODO

        final Employee employee = new Employee();
        employee.setId(id);
        employee.setSupervisor(supervisor);
        employee.setAddress(AddressDataFactory.buildAddress(2L));
        employee.setGender(EmployeeGender.MALE);
        employee.setCpf("CPF OF " + id.toString());
        employee.setDateOfBirth(LocalDate.parse("1996-10-06"));
        employee.setName("NAME OF " + id.toString());
        employee.setSalary(new BigDecimal(1000));
//        employee.setProjectList(); TODO

        return employee;
    }


    public static EmployeeForm buildEmployeeDtoRequest(Long id){

        final EmployeeForm employee = new EmployeeForm();
        employee.setIdSupervisor(id + 1000);
        employee.setIdAddress(2L);
        employee.setGender(EmployeeGender.MALE.toString());
        employee.setCpf("CPF OF " + id.toString());
        employee.setDateOfBirth(LocalDate.parse("1996-10-06"));
        employee.setName("NAME OF " + id.toString());
        employee.setSalary(new BigDecimal(1000));
//        employee.setProjectList(); TODO

        return employee;
    }
}
