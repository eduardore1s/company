package com.hotmart.api.company.controller;

import com.hotmart.api.company.model.dto.request.ProjectDtoRequest;
import com.hotmart.api.company.model.dto.response.ProjectDtoResponse;
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
    private ProjectService projectsService;

    @GetMapping
    public ResponseEntity<?> getProjects(){

        final List<ProjectDtoResponse> projectsDtoResponseList = projectsService.findAll();

        if (projectsDtoResponseList != null) {
            return ResponseEntity.ok(projectsDtoResponseList);
        }
        return ResponseEntity.noContent().build();
    }

    @PostMapping
    public ResponseEntity<ProjectDtoResponse> postProject(@Valid @RequestBody ProjectDtoRequest projectsDtoRequest,
                                                                UriComponentsBuilder uriBuilder){

        final ProjectDtoResponse projectsDtoResponse = projectsService.create(projectsDtoRequest);

        if (projectsDtoResponse != null){
            final URI uri = uriBuilder.path("api/v1/projects/{id}").buildAndExpand(projectsDtoResponse.getId()).toUri();
            return ResponseEntity.created(uri).body(projectsDtoResponse);
        }
        return ResponseEntity.unprocessableEntity().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getProject(@PathVariable Long id){

        final ProjectDtoResponse projectsDtoResponse = projectsService.findById(id);

        if (projectsDtoResponse != null) {
            return ResponseEntity.ok(projectsDtoResponse);
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProjectDtoResponse> putProject(@PathVariable Long id,
                                                               @Valid @RequestBody ProjectDtoRequest projectsDtoRequest){

        final ProjectDtoResponse projectsDtoResponse = projectsService.update(id, projectsDtoRequest);

        if (projectsDtoResponse != null){
            return new ResponseEntity<>(projectsDtoResponse, HttpStatus.CREATED);
        }

        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ProjectDtoResponse> deleteProject(@PathVariable Long id){

        if (projectsService.delete(id)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
