package com.hotmart.api.company.controller;

import com.hotmart.api.company.model.dto.request.EmployeeDtoRequest;
import com.hotmart.api.company.model.dto.response.EmployeeDtoResponse;
import com.hotmart.api.company.model.dto.response.ProjectDtoResponse;
import com.hotmart.api.company.services.business.EmployeeService;
import com.hotmart.api.company.services.business.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/v1/employees")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private ProjectService projectService;

    @GetMapping
    public ResponseEntity<?> getEmployees(){

        final List<EmployeeDtoResponse> employeeDtoResponseList = employeeService.findAll();

        if (employeeDtoResponseList != null) {
            return ResponseEntity.ok(employeeDtoResponseList);
        }
        return ResponseEntity.noContent().build();
    }

    @PostMapping
    public ResponseEntity<EmployeeDtoResponse> postEmployee(@Valid @RequestBody EmployeeDtoRequest employeeDtoRequest,
                                                          UriComponentsBuilder uriBuilder){

        final EmployeeDtoResponse employeeDtoResponse = employeeService.create(employeeDtoRequest);

        if (employeeDtoResponse != null){
            final URI uri = uriBuilder.path("api/v1/employee/{id}").buildAndExpand(employeeDtoResponse.getId()).toUri();
            return ResponseEntity.created(uri).body(employeeDtoResponse);
        }
        return ResponseEntity.unprocessableEntity().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getEmployee(@PathVariable Long id){

        final EmployeeDtoResponse employeeDtoResponse = employeeService.findById(id);

        if (employeeDtoResponse != null) {
            return ResponseEntity.ok(employeeDtoResponse);
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<EmployeeDtoResponse> putEmployee(@PathVariable Long id,
                                                         @Valid @RequestBody EmployeeDtoRequest employeeDtoRequest){

        final EmployeeDtoResponse employeeDtoResponse = employeeService.update(id, employeeDtoRequest);

        if (employeeDtoResponse != null){
            return new ResponseEntity<>(employeeDtoResponse, HttpStatus.CREATED);
        }

        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<EmployeeDtoResponse> deleteEmployee(@PathVariable Long id){

        if (employeeService.delete(id)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }


    @GetMapping("/search")
    public ResponseEntity<?> getEmployeeByName(@RequestParam String name){

        final EmployeeDtoResponse employeeDtoResponse = employeeService.findByName(name);

        if (employeeDtoResponse != null) {
            return ResponseEntity.ok(employeeDtoResponse);
        }
        return ResponseEntity.notFound().build();
    }


    @GetMapping("/{id}/projects")
    public ResponseEntity<?> getProjectsByEmployee(@PathVariable Long id){

        final List<ProjectDtoResponse> projectsDtoResponseList = projectService.findByEmployeeListId(id);

        if (projectsDtoResponseList != null) {
            return ResponseEntity.ok(projectsDtoResponseList);
        }
        return ResponseEntity.noContent().build();
    }
}
