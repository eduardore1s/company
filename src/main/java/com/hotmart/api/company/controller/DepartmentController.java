package com.hotmart.api.company.controller;

import com.hotmart.api.company.model.form.DepartmentForm;
import com.hotmart.api.company.model.vo.DepartmentVo;
import com.hotmart.api.company.model.vo.EmployeeVo;
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

        final List<DepartmentVo> departmentVoList = departmentService.findAll();

        if (departmentVoList != null) {
            return ResponseEntity.ok(departmentVoList);
        }
        return ResponseEntity.noContent().build();
    }

    @PostMapping
    public ResponseEntity<DepartmentVo> postDepartment(@Valid @RequestBody DepartmentForm departmentForm,
                                                       UriComponentsBuilder uriBuilder){

        final DepartmentVo departmentVo = departmentService.create(departmentForm);

        if (departmentVo != null){
            final URI uri = uriBuilder.path("api/v1/department/{id}").buildAndExpand(departmentVo.getId()).toUri();
            return ResponseEntity.created(uri).body(departmentVo);
        }
        return ResponseEntity.unprocessableEntity().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getDepartment(@PathVariable Long id){

        final DepartmentVo departmentVo = departmentService.findById(id);

        if (departmentVo != null) {
            return ResponseEntity.ok(departmentVo);
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<DepartmentVo> putDepartment(@PathVariable Long id,
                                                      @Valid @RequestBody DepartmentForm departmentForm){

        final DepartmentVo departmentVo = departmentService.update(id, departmentForm);

        if (departmentVo != null){
            return new ResponseEntity<>(departmentVo, HttpStatus.CREATED);
        }

        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<DepartmentVo> deleteDepartment(@PathVariable Long id){

        if (departmentService.delete(id)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("{id}/employees")
    public ResponseEntity<?> getEmployeesOfDepartment(@PathVariable Long id){

        final List<EmployeeVo> employeeVoList = employeeService.findByProjectListDepartmentId(id);

        if (employeeVoList != null) {
            return ResponseEntity.ok(employeeVoList);
        }
        return ResponseEntity.noContent().build();
    }

}
