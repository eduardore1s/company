package com.hotmart.api.company.data;

import com.hotmart.api.company.model.dto.request.DepartmentDtoRequest;
import com.hotmart.api.company.model.entity.Budget;
import com.hotmart.api.company.model.entity.Department;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class DepartmentDataFactory {

    public static Department buildDepartment(Long id){

        final Budget budget = new Budget();
        budget.setValue(new BigDecimal(2500));

        final List<Budget> budgets = new ArrayList<>();
        budgets.add(budget);

        final Department department = new Department();
        department.setId(id);
        department.setName("NAME OF ID " + id.toString());
        department.setNumber(id.intValue());
        department.setBudgets(budgets);

        return department;
    }


    public static DepartmentDtoRequest buildDepartmentDtoRequest(Long id){

        final List<Long> idBudgets = new ArrayList<>();
        idBudgets.add(1L);

        final DepartmentDtoRequest departmentDtoRequest = new DepartmentDtoRequest();
        departmentDtoRequest.setName("NAME OF ID " + id.toString());
        departmentDtoRequest.setNumber(id.intValue());
//        departmentDtoRequest.setIdBudgets(idBudgets); TODO

        return departmentDtoRequest;
    }
}
