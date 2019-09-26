package com.hotmart.api.company.services.business;

import com.hotmart.api.company.model.dto.request.ProjectDtoRequest;
import com.hotmart.api.company.model.dto.response.ProjectDtoResponse;
import com.hotmart.api.company.model.entity.Department;
import com.hotmart.api.company.model.entity.Project;
import com.hotmart.api.company.model.mapper.ProjectMapper;
import com.hotmart.api.company.services.data.DepartmentDataService;
import com.hotmart.api.company.services.data.ProjectDataService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProjectServiceImpl implements ProjectService{

    private final ProjectMapper projectMapper;

    private final ProjectDataService projectDataService;

    private final DepartmentDataService departmentDataService;

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

        final Project project = projectMapper.toProject(projectDtoRequest);

        setDepartmentProject(projectDtoRequest, project);

        return projectMapper.toProjectDtoResponse(projectDataService.save(project));
    }

    @Override
    public ProjectDtoResponse update(Long id, ProjectDtoRequest projectDtoRequest) {

        final Optional<Project> projectOptional = projectDataService.findById(id);

        if (projectOptional.isPresent()) {

            final Project project = projectOptional.get();
            projectMapper.updateProject(projectDtoRequest, project);

            setDepartmentProject(projectDtoRequest, project);

            return projectMapper.toProjectDtoResponse(projectDataService.save(project));
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

    @Override
    public List<ProjectDtoResponse> findByEmployeeListId(Long idEmployee) {

        final List<Project>  projectList = projectDataService.findByEmployeeListId(idEmployee);
        if (!projectList.isEmpty()){
            return projectList.stream().map(projectMapper::toProjectDtoResponse).collect(Collectors.toList());
        }
        return null;
    }

    private void setDepartmentProject(ProjectDtoRequest projectDtoRequest, Project project) {
        if (projectDtoRequest.getIdDepartment() != null){
            final Optional<Department> departmentProject = departmentDataService.findById(projectDtoRequest.getIdDepartment());

            if (departmentProject.isPresent()){
                project.setDepartment(departmentProject.get());
            }
        }
    }

}
