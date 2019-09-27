package com.hotmart.api.company.controller;

import com.hotmart.api.company.model.vo.EmployeeVo;
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

        final List<EmployeeVo> employeeVoList = employeeService.findBySupervisorId(id);

        if (employeeVoList != null) {
            return ResponseEntity.ok(employeeVoList);
        }
        return ResponseEntity.noContent().build();
    }

}
