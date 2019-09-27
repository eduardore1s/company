package com.hotmart.api.company.services.business;

import com.hotmart.api.company.controller.form.ProjectForm;
import com.hotmart.api.company.controller.vo.ProjectVo;
import com.hotmart.api.company.model.entity.Department;
import com.hotmart.api.company.model.entity.Project;
import com.hotmart.api.company.model.mapper.ProjectMapper;
import com.hotmart.api.company.repository.DepartmentRepository;
import com.hotmart.api.company.repository.ProjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProjectService {

    private final ProjectMapper projectMapper;

    private final ProjectRepository projectRepository;

    private final DepartmentRepository departmentRepository;

    public List<ProjectVo> findAll() {

        final List<Project>  projectList = projectRepository.findAll();
        if (!projectList.isEmpty()){
            return projectList.stream().map(projectMapper::toProjectDtoResponse).collect(Collectors.toList());
        }
        return null;
    }

    public ProjectVo create(ProjectForm projectForm) {

        final Project project = projectMapper.toProject(projectForm);

        setDepartmentProject(projectForm, project);

        return projectMapper.toProjectDtoResponse(projectRepository.save(project));
    }

    public ProjectVo update(Long id, ProjectForm projectForm) {

        final Optional<Project> projectOptional = projectRepository.findById(id);

        if (projectOptional.isPresent()) {

            final Project project = projectOptional.get();
            projectMapper.updateProject(projectForm, project);

            setDepartmentProject(projectForm, project);

            return projectMapper.toProjectDtoResponse(projectRepository.save(project));
        }
        return null;
    }

    public boolean delete(Long id) {
        final Optional<Project> projectOptional = projectRepository.findById(id);

        if (projectOptional.isPresent()){
            projectRepository.delete(projectOptional.get());
            return true;
        }

        return false;
    }

    public ProjectVo findById(Long id) {

        final Optional<Project> project = projectRepository.findById(id);

        if (project.isPresent()){
            return projectMapper.toProjectDtoResponse(project.get());
        }

        return null;
    }

    public List<ProjectVo> findByEmployeeListId(Long idEmployee) {

        final List<Project>  projectList = projectRepository.findByEmployeeListId(idEmployee);
        if (!projectList.isEmpty()){
            return projectList.stream().map(projectMapper::toProjectDtoResponse).collect(Collectors.toList());
        }
        return null;
    }

    private void setDepartmentProject(ProjectForm projectForm, Project project) {
        if (projectForm.getIdDepartment() != null){
            final Optional<Department> departmentProject = departmentRepository.findById(projectForm.getIdDepartment());

            if (departmentProject.isPresent()){
                project.setDepartment(departmentProject.get());
            }
        }
    }

}
