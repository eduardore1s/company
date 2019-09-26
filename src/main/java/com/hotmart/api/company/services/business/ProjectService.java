package com.hotmart.api.company.services.business;

import com.hotmart.api.company.model.dto.request.ProjectDtoRequest;
import com.hotmart.api.company.model.dto.response.ProjectDtoResponse;

import java.util.List;

public interface ProjectService {

    List<ProjectDtoResponse> findAll();
    ProjectDtoResponse create(ProjectDtoRequest projectDtoRequest);
    ProjectDtoResponse update(Long id, ProjectDtoRequest projectDtoRequest);
    boolean delete(Long id);
    ProjectDtoResponse findById(Long id);
    List<ProjectDtoResponse> findByEmployeeListId(Long idEmployee);
}
