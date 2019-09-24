package com.hotmart.api.company.services.business;

import com.hotmart.api.company.model.dto.request.ProjectDtoRequest;
import com.hotmart.api.company.model.dto.response.ProjectDtoResponse;
import com.hotmart.api.company.model.entity.Department;
import com.hotmart.api.company.model.entity.Project;
import com.hotmart.api.company.model.mapper.ProjectMapper;
import com.hotmart.api.company.services.data.DepartmentDataService;
import com.hotmart.api.company.services.data.ProjectDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProjectServiceImpl implements ProjectService{

    @Autowired
    private ProjectMapper projectMapper;

    @Autowired
    private ProjectDataService projectDataService;

    @Autowired
    private DepartmentDataService departmentDataService;

    @Override
    public List<ProjectDtoResponse> findAll() {

        final List<Project>  projectList = projectDataService.findAll();
        if (!projectList.isEmpty()){
            return projectList.stream().map(projectMapper::toProjectDtoResponse).collect(Collectors.toList());
        }
        return null;
    }

    @Override
    public ProjectDtoResponse create(ProjectDtoRequest projectDtoRequest) {

        Project projectResponse = null;

        final Optional<Department> departmentProject = departmentDataService.findById(projectDtoRequest.getIdDepartment());

        if (departmentProject.isPresent()){
            final Project project = projectMapper.toProject(projectDtoRequest);
            project.setDepartment(departmentProject.get());

            projectResponse = projectDataService.save(project);
        }

        return projectMapper.toProjectDtoResponse(projectResponse);
    }

    @Override
    public ProjectDtoResponse update(Long id, ProjectDtoRequest projectDtoRequest) {

        final Optional<Project> projectOptional = projectDataService.findById(id);
        final Optional<Department> departmentProject = departmentDataService.findById(projectDtoRequest.getIdDepartment());

        if (projectOptional.isPresent() && departmentProject.isPresent()) {

            final Project project = projectOptional.get();
            projectMapper.updateProject(projectDtoRequest, project);
            project.setDepartment(departmentProject.get());

            projectDataService.save(project);

            return projectMapper.toProjectDtoResponse(project);
        }
        return null;
    }

    @Override
    public boolean delete(Long id) {
        final Optional<Project> projectOptional = projectDataService.findById(id);

        if (projectOptional.isPresent()){
            projectDataService.delete(projectOptional.get());
            return true;
        }

        return false;
    }

    @Override
    public ProjectDtoResponse findById(Long id) {

        final Optional<Project> project = projectDataService.findById(id);

        if (project.isPresent()){
            return projectMapper.toProjectDtoResponse(project.get());
        }

        return null;
    }
}
