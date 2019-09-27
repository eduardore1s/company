package com.hotmart.api.company.services.business;

import com.hotmart.api.company.controller.form.ProjectForm;
import com.hotmart.api.company.controller.vo.ProjectVo;
import com.hotmart.api.company.model.entity.Department;
import com.hotmart.api.company.model.entity.Project;
import com.hotmart.api.company.model.exception.GenericErrorException;
import com.hotmart.api.company.model.exception.ResourceNotFoundException;
import com.hotmart.api.company.model.mapper.ProjectMapper;
import com.hotmart.api.company.repository.DepartmentRepository;
import com.hotmart.api.company.repository.ProjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
            return projectList.stream().map(projectMapper::toProjectVo).collect(Collectors.toList());
        }
        return new ArrayList<>();
    }

    public ProjectVo create(ProjectForm projectForm) throws GenericErrorException {

        final Project project = projectMapper.toProject(projectForm);
        setDepartmentProject(projectForm, project);

        final Project projectSaved = projectRepository.save(project);
        if (projectSaved != null){
            return projectMapper.toProjectVo(projectSaved);
        }
        throw new GenericErrorException(null, "Ocorreu um erro ao processar a criação deste recurso.");
    }

    public ProjectVo update(Long id, ProjectForm projectForm) throws ResourceNotFoundException {

        final Optional<Project> projectOptional = projectRepository.findById(id);

        if (projectOptional.isPresent()) {

            final Project project = projectOptional.get();
            projectMapper.updateProject(projectForm, project);

            setDepartmentProject(projectForm, project);

            return projectMapper.toProjectVo(projectRepository.save(project));
        }
        throw new ResourceNotFoundException("id", "Nao existe Project para este id.");
    }

    public void delete(Long id) throws ResourceNotFoundException {
        final Optional<Project> projectOptional = projectRepository.findById(id);

        if (!projectOptional.isPresent()){
            throw new ResourceNotFoundException("id", "Nao existe Project para este id.");
        }
        projectRepository.delete(projectOptional.get());
    }

    public ProjectVo findById(Long id) throws ResourceNotFoundException {

        final Optional<Project> project = projectRepository.findById(id);

        if (project.isPresent()){
            return projectMapper.toProjectVo(project.get());
        }
        throw new ResourceNotFoundException("id", "Nao existe Project para este id.");
    }

    public List<ProjectVo> findByEmployeeListId(Long idEmployee) {

        final List<Project> projectList = projectRepository.findByEmployeeListId(idEmployee);
        if (!projectList.isEmpty()){
            return projectList.stream().map(projectMapper::toProjectVo).collect(Collectors.toList());
        }
        return new ArrayList<>();
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
