package com.hotmart.api.company.controller;

import com.hotmart.api.company.model.form.EmployeeForm;
import com.hotmart.api.company.model.vo.EmployeeVo;
import com.hotmart.api.company.model.vo.ProjectVo;
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

        final List<EmployeeVo> employeeVoList = employeeService.findAll();

        if (employeeVoList != null) {
            return ResponseEntity.ok(employeeVoList);
        }
        return ResponseEntity.noContent().build();
    }

    @PostMapping
    public ResponseEntity<EmployeeVo> postEmployee(@Valid @RequestBody EmployeeForm employeeForm,
                                                   UriComponentsBuilder uriBuilder){

        final EmployeeVo employeeVo = employeeService.create(employeeForm);

        if (employeeVo != null){
            final URI uri = uriBuilder.path("api/v1/employee/{id}").buildAndExpand(employeeVo.getId()).toUri();
            return ResponseEntity.created(uri).body(employeeVo);
        }
        return ResponseEntity.unprocessableEntity().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getEmployee(@PathVariable Long id){

        final EmployeeVo employeeVo = employeeService.findById(id);

        if (employeeVo != null) {
            return ResponseEntity.ok(employeeVo);
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<EmployeeVo> putEmployee(@PathVariable Long id,
                                                  @Valid @RequestBody EmployeeForm employeeForm){

        final EmployeeVo employeeVo = employeeService.update(id, employeeForm);

        if (employeeVo != null){
            return new ResponseEntity<>(employeeVo, HttpStatus.CREATED);
        }

        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<EmployeeVo> deleteEmployee(@PathVariable Long id){

        if (employeeService.delete(id)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }


    @GetMapping("/search")
    public ResponseEntity<?> getEmployeeByName(@RequestParam String name){

        final EmployeeVo employeeVo = employeeService.findByName(name);

        if (employeeVo != null) {
            return ResponseEntity.ok(employeeVo);
        }
        return ResponseEntity.notFound().build();
    }


    @GetMapping("/{id}/projects")
    public ResponseEntity<?> getProjectsByEmployee(@PathVariable Long id){

        final List<ProjectVo> projectsDtoResponseList = projectService.findByEmployeeListId(id);

        if (projectsDtoResponseList != null) {
            return ResponseEntity.ok(projectsDtoResponseList);
        }
        return ResponseEntity.noContent().build();
    }
}
