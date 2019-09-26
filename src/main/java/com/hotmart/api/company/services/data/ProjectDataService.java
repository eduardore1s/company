package com.hotmart.api.company.services.data;

import com.hotmart.api.company.model.entity.Project;

import java.util.List;
import java.util.Optional;

public interface ProjectDataService {

    List<Project> findAll();
    Project save(Project project);
    void delete(Project project);
    Optional<Project> findById(Long id);
    List<Project> findByEmployeeListId(Long idEmployee);
}
