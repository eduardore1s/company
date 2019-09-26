package com.hotmart.api.company.controller;

import com.hotmart.api.company.model.dto.request.DepartmentDtoRequest;
import com.hotmart.api.company.model.dto.response.DepartmentDtoResponse;
import com.hotmart.api.company.model.dto.response.EmployeeDtoResponse;
import com.hotmart.api.company.services.business.DepartmentService;
import com.hotmart.api.company.services.business.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("api/v1/departments")
public class DepartmentController {
    
    @Autowired
    private DepartmentService departmentService;

    @Autowired
    private EmployeeService employeeService;

    @GetMapping
    public ResponseEntity<?> getDepartments(){

        final List<DepartmentDtoResponse> departmentDtoResponseList = departmentService.findAll();

        if (departmentDtoResponseList != null) {
            return ResponseEntity.ok(departmentDtoResponseList);
        }
        return ResponseEntity.noContent().build();
    }

    @PostMapping
    public ResponseEntity<DepartmentDtoResponse> postDepartment(@Valid @RequestBody DepartmentDtoRequest departmentDtoRequest,
                                                            UriComponentsBuilder uriBuilder){

        final DepartmentDtoResponse departmentDtoResponse = departmentService.create(departmentDtoRequest);

        if (departmentDtoResponse != null){
            final URI uri = uriBuilder.path("api/v1/department/{id}").buildAndExpand(departmentDtoResponse.getId()).toUri();
            return ResponseEntity.created(uri).body(departmentDtoResponse);
        }
        return ResponseEntity.unprocessableEntity().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getDepartment(@PathVariable Long id){

        final DepartmentDtoResponse departmentDtoResponse = departmentService.findById(id);

        if (departmentDtoResponse != null) {
            return ResponseEntity.ok(departmentDtoResponse);
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<DepartmentDtoResponse> putDepartment(@PathVariable Long id,
                                                           @Valid @RequestBody DepartmentDtoRequest departmentDtoRequest){

        final DepartmentDtoResponse departmentDtoResponse = departmentService.update(id, departmentDtoRequest);

        if (departmentDtoResponse != null){
            return new ResponseEntity<>(departmentDtoResponse, HttpStatus.CREATED);
        }

        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<DepartmentDtoResponse> deleteDepartment(@PathVariable Long id){

        if (departmentService.delete(id)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }


    @GetMapping("{idDepartment}/employees")
    public ResponseEntity<?> getEmployeesOfDepartment(@PathVariable Long idDepartment){

        final List<EmployeeDtoResponse> employeeDtoResponseList = employeeService.findByProjectListDepartmentId(idDepartment);

        if (employeeDtoResponseList != null) {
            return ResponseEntity.ok(employeeDtoResponseList);
        }
        return ResponseEntity.noContent().build();

    }
}
