package com.hotmart.api.company.controller;

import com.hotmart.api.company.controller.form.ProjectEmployeesForm;
import com.hotmart.api.company.controller.form.ProjectForm;
import com.hotmart.api.company.controller.vo.EmployeeVo;
import com.hotmart.api.company.controller.vo.ProjectVo;
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
@RequestMapping("/api/v1/projects")
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    @GetMapping
    public ResponseEntity<?> getProjects() {
        return ResponseEntity.ok(projectService.findAll());
    }

    @PostMapping
    public ResponseEntity<ProjectVo> postProject(@Valid @RequestBody ProjectForm projectsDtoRequest,
                                                 UriComponentsBuilder uriBuilder) {

        final ProjectVo projectsDtoResponse = projectService.create(projectsDtoRequest);
        final URI uri = uriBuilder.path("api/v1/projects/{id}").buildAndExpand(projectsDtoResponse.getId()).toUri();
        return ResponseEntity.created(uri).body(projectsDtoResponse);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getProject(@PathVariable Long id) {
        return ResponseEntity.ok(projectService.findById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProjectVo> putProject(@PathVariable Long id,
                                                @Valid @RequestBody ProjectForm projectsDtoRequest) {

        final ProjectVo projectsDtoResponse = projectService.update(id, projectsDtoRequest);
        return new ResponseEntity<>(projectsDtoResponse, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ProjectVo> deleteProject(@PathVariable Long id) {
        projectService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("{id}/employees")
    public ResponseEntity<List<EmployeeVo>> postProjectEmployees (@PathVariable Long id, @Valid @RequestBody ProjectEmployeesForm projectEmployessForm) {
        final List<EmployeeVo> projectEmployees = projectService.setProjectEmployees(id, projectEmployessForm);
        return new ResponseEntity<>(projectEmployees, HttpStatus.CREATED);
    }
}
