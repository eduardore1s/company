package com.hotmart.api.company.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/employees")
public class EmployeeController {

    @GetMapping
    public ResponseEntity<?> getEmployees(){
        return ResponseEntity.ok("teste");
    }
}
