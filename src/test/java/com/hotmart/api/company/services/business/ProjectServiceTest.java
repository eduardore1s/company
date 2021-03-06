package com.hotmart.api.company.services.business;

import com.hotmart.api.company.data.ProjectDataFactory;
import com.hotmart.api.company.controller.form.ProjectForm;
import com.hotmart.api.company.controller.vo.ProjectVo;
import com.hotmart.api.company.model.entity.Department;
import com.hotmart.api.company.model.entity.Project;
import com.hotmart.api.company.model.exception.GenericErrorException;
import com.hotmart.api.company.model.exception.ResourceNotFoundException;
import com.hotmart.api.company.model.mapper.ProjectMapper;
import com.hotmart.api.company.model.mapper.ProjectMapperImpl;
import com.hotmart.api.company.repository.DepartmentRepository;
import com.hotmart.api.company.repository.EmployeeRepository;
import com.hotmart.api.company.repository.ProjectRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RunWith(MockitoJUnitRunner.class)
public class ProjectServiceTest {

    private ProjectService projectServiceImpl;

    private ProjectMapper projectMapper;

    @Mock
    private ProjectRepository projectRepository;

    @Mock
    private DepartmentRepository departmentRepository;

    @Mock
    private EmployeeRepository employeeRepository;

    @Before
    public void init(){
        projectMapper = new ProjectMapperImpl();
        projectServiceImpl = new ProjectService(projectMapper, projectRepository, departmentRepository, employeeRepository);
    }

    @Test
    public void findAllShouldReturnListProjectDtoResponseWith3Elements(){
        final List<Project> projectList = new ArrayList<>();
        projectList.add(ProjectDataFactory.buildProject(1L));
        projectList.add(ProjectDataFactory.buildProject(2L));
        projectList.add(ProjectDataFactory.buildProject(3L));
        Mockito.when(projectRepository.findAll()).thenReturn(projectList);

        final List<ProjectVo> employeeListDtoResponse = projectServiceImpl.findAll();

        Assert.assertTrue(employeeListDtoResponse.size() == 3);
        Assert.assertEquals(projectList.get(0).getId(), employeeListDtoResponse.get(0).getId());
        Assert.assertEquals(projectList.get(0).getName(), employeeListDtoResponse.get(0).getName());
        Assert.assertEquals(projectList.get(0).getDepartment().getId(), employeeListDtoResponse.get(0).getDepartment().getId());
        Assert.assertEquals(projectList.get(0).getDateStart(), employeeListDtoResponse.get(0).getDateStart());
        Assert.assertEquals(projectList.get(0).getDateFinal(), employeeListDtoResponse.get(0).getDateFinal());
        Assert.assertEquals(projectList.get(0).getValue(), employeeListDtoResponse.get(0).getValue());
        Assert.assertEquals(projectList.get(0).getEmployeeList().get(0).getId(), employeeListDtoResponse.get(0).getEmployeeList().get(0).getId());

        Assert.assertEquals(projectList.get(1).getName(), employeeListDtoResponse.get(1).getName());
        Assert.assertEquals(projectList.get(2).getName(), employeeListDtoResponse.get(2).getName());
    }

    @Test
    public void findAllShouldReturnListEmpty(){
        Mockito.when(projectRepository.findAll()).thenReturn(new ArrayList<>());

        final List<ProjectVo> projectListDtoResponse = projectServiceImpl.findAll();

        Assert.assertNotNull(projectListDtoResponse);
        Assert.assertTrue(projectListDtoResponse.isEmpty());
    }

    @Test
    public void createShouldReturnProjectDtoResponse() throws GenericErrorException {
        final ProjectForm projectForm = ProjectDataFactory.buildProjectDtoRequest(1L);

        final Department department = new Department();
        department.setId(projectForm.getIdDepartment());
        final Optional<Department> departmentProject = Optional.of(department);
        Mockito.when(departmentRepository.findById(projectForm.getIdDepartment())).thenReturn(departmentProject);

        final Project project = projectMapper.toProject(projectForm);
        project.setDepartment(departmentProject.get());
        Mockito.when(projectRepository.save(Mockito.any())).thenReturn(project);

        final ProjectVo projectVo = projectServiceImpl.create(projectForm);

        Assert.assertEquals(project.getId(), projectVo.getId());
        Assert.assertEquals(project.getName(), projectVo.getName());
        Assert.assertEquals(project.getDepartment().getId(), projectVo.getDepartment().getId());
        Assert.assertEquals(project.getDateStart(), projectVo.getDateStart());
        Assert.assertEquals(project.getDateFinal(), projectVo.getDateFinal());
        Assert.assertEquals(project.getValue(), projectVo.getValue());
//        Assert.assertEquals(project.getEmployeeList().get(0).getId(), projectDtoResponse.getEmployeeList().get(0).getId()); TODO
    }

    @Test
    public void updateShouldReturnEmployeeDtoResponse() throws ResourceNotFoundException {
        final Optional<Project> optionalProject = Optional.of(ProjectDataFactory.buildProject(1L));
        Mockito.when(projectRepository.findById(1L)).thenReturn(optionalProject);

        final ProjectForm projectForm = ProjectDataFactory.buildProjectDtoRequest(2L);

        final Department department = new Department();
        department.setId(projectForm.getIdDepartment());
        final Optional<Department> departmentProject = Optional.of(department);
        Mockito.when(departmentRepository.findById(projectForm.getIdDepartment())).thenReturn(departmentProject);

        final Project project = projectMapper.toProject(projectForm);
        project.setDepartment(departmentProject.get());
        Mockito.when(projectRepository.save(Mockito.any())).thenReturn(project);

        final ProjectVo projectVo = projectServiceImpl.update(1L, projectForm);

        Assert.assertEquals(project.getId(), projectVo.getId());
        Assert.assertEquals(project.getName(), projectVo.getName());
        Assert.assertEquals(project.getDepartment().getId(), projectVo.getDepartment().getId());
        Assert.assertEquals(project.getDateStart(), projectVo.getDateStart());
        Assert.assertEquals(project.getDateFinal(), projectVo.getDateFinal());
        Assert.assertEquals(project.getValue(), projectVo.getValue());
//        Assert.assertEquals(project.getEmployeeList().get(0).getId(), projectDtoResponse.getEmployeeList().get(0).getId()); TODO
    }

    @Test(expected = ResourceNotFoundException.class)
    public void updateShouldReturnResourceNotFound() throws ResourceNotFoundException {
        Mockito.when(projectRepository.findById(1L)).thenReturn(Optional.empty());

        projectServiceImpl.update(1L, null);
    }

    @Test(expected = ResourceNotFoundException.class)
    public void deleteShouldReturnFalse() throws ResourceNotFoundException {
        Mockito.when(projectRepository.findById(1L)).thenReturn(Optional.empty());

        projectServiceImpl.delete(1L);
    }

    @Test
    public void findByIdShouldReturnProjectDtoResponse() throws ResourceNotFoundException {
        final Project project = ProjectDataFactory.buildProject(1L);
        Mockito.when(projectRepository.findById(1L)).thenReturn(Optional.of(project));

        final ProjectVo projectVo = projectServiceImpl.findById(1L);

        Assert.assertEquals(project.getId(), projectVo.getId());
        Assert.assertEquals(project.getName(), projectVo.getName());
        Assert.assertEquals(project.getDepartment().getId(), projectVo.getDepartment().getId());
        Assert.assertEquals(project.getDateStart(), projectVo.getDateStart());
        Assert.assertEquals(project.getDateFinal(), projectVo.getDateFinal());
        Assert.assertEquals(project.getValue(), projectVo.getValue());
        Assert.assertEquals(project.getEmployeeList().get(0).getId(), projectVo.getEmployeeList().get(0).getId());

    }

    @Test(expected = ResourceNotFoundException.class)
    public void findByIdShouldReturnResourceNotFound() throws ResourceNotFoundException {
        Mockito.when(projectRepository.findById(1L)).thenReturn(Optional.empty());

        projectServiceImpl.findById(1L);
    }



}
