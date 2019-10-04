package com.hotmart.api.company.controller;

import com.hotmart.api.company.controller.form.EmployeeForm;
import com.hotmart.api.company.controller.vo.EmployeeVo;
import com.hotmart.api.company.services.business.EmployeeService;
import com.hotmart.api.company.services.business.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/api/v1/employees")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private ProjectService projectService;

    @GetMapping
    public ResponseEntity<?> getEmployees() {
        return ResponseEntity.ok(employeeService.findAll());
    }

    @PostMapping
    public ResponseEntity<EmployeeVo> postEmployee(@Valid @RequestBody EmployeeForm employeeForm,
                                                   UriComponentsBuilder uriBuilder) {
        final EmployeeVo employeeVo = employeeService.create(employeeForm);
        final URI uri = uriBuilder.path("api/v1/employee/{id}").buildAndExpand(employeeVo.getId()).toUri();
        return ResponseEntity.created(uri).body(employeeVo);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getEmployee(@PathVariable Long id) {
        return ResponseEntity.ok(employeeService.findById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<EmployeeVo> putEmployee(@PathVariable Long id,
                                                  @Valid @RequestBody EmployeeForm employeeForm) {
        final EmployeeVo employeeVo = employeeService.update(id, employeeForm);
        return new ResponseEntity<>(employeeVo, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<EmployeeVo> deleteEmployee(@PathVariable Long id) {
        employeeService.delete(id);
        return ResponseEntity.noContent().build();
    }


    @GetMapping("/search")
    public ResponseEntity<?> getEmployeeByName(@RequestParam String name) {
        return ResponseEntity.ok(employeeService.findByName(name));
    }


    @GetMapping("/{id}/projects")
    public ResponseEntity<?> getProjectsByEmployee(@PathVariable Long id) {
        return ResponseEntity.ok(projectService.findByEmployeeListId(id));
    }
}
