package com.hotmart.api.company.controller;

import com.hotmart.api.company.model.dto.response.EmployeeDtoResponse;
import com.hotmart.api.company.services.business.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1/supervisors")
public class SupervisorController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/{id}/employees")
    public ResponseEntity<?> getEmployeesBySupervisor(@PathVariable Long id){

        final List<EmployeeDtoResponse> employeeDtoResponseList = employeeService.findBySupervisorId(id);

        if (employeeDtoResponseList != null) {
            return ResponseEntity.ok(employeeDtoResponseList);
        }
        return ResponseEntity.noContent().build();
    }

}
