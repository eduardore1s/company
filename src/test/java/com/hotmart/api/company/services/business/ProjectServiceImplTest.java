package com.hotmart.api.company.services.business;

import com.hotmart.api.company.data.ProjectDataFactory;
import com.hotmart.api.company.model.dto.request.ProjectDtoRequest;
import com.hotmart.api.company.model.dto.response.ProjectDtoResponse;
import com.hotmart.api.company.model.entity.Department;
import com.hotmart.api.company.model.entity.Project;
import com.hotmart.api.company.model.mapper.ProjectMapper;
import com.hotmart.api.company.model.mapper.ProjectMapperImpl;
import com.hotmart.api.company.services.data.DepartmentDataService;
import com.hotmart.api.company.services.data.ProjectDataService;
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
public class ProjectServiceImplTest {

    private ProjectServiceImpl projectServiceImpl;

    private ProjectMapper projectMapper;

    @Mock
    private ProjectDataService projectDataService;

    @Mock
    private DepartmentDataService departmentDataService;

    @Before
    public void init(){
        projectMapper = new ProjectMapperImpl();
        projectServiceImpl = new ProjectServiceImpl(projectMapper, projectDataService, departmentDataService);
    }

    @Test
    public void findAllShouldReturnListProjectDtoResponseWith3Elements(){
        final List<Project> projectList = new ArrayList<>();
        projectList.add(ProjectDataFactory.buildProject(1L));
        projectList.add(ProjectDataFactory.buildProject(2L));
        projectList.add(ProjectDataFactory.buildProject(3L));
        Mockito.when(projectDataService.findAll()).thenReturn(projectList);

        final List<ProjectDtoResponse> employeeListDtoResponse = projectServiceImpl.findAll();

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
    public void findAllShouldReturnNull(){
        Mockito.when(projectDataService.findAll()).thenReturn(new ArrayList<>());

        final List<ProjectDtoResponse> projectListDtoResponse = projectServiceImpl.findAll();

        Assert.assertNull(projectListDtoResponse);
    }

    @Test
    public void createShouldReturnProjectDtoResponse(){
        final ProjectDtoRequest projectDtoRequest = ProjectDataFactory.buildProjectDtoRequest(1L);

        final Department department = new Department();
        department.setId(projectDtoRequest.getIdDepartment());
        final Optional<Department> departmentProject = Optional.of(department);
        Mockito.when(departmentDataService.findById(projectDtoRequest.getIdDepartment())).thenReturn(departmentProject);

        final Project project = projectMapper.toProject(projectDtoRequest);
        project.setDepartment(departmentProject.get());
        Mockito.when(projectDataService.save(Mockito.any())).thenReturn(project);

        final ProjectDtoResponse projectDtoResponse = projectServiceImpl.create(projectDtoRequest);

        Assert.assertEquals(project.getId(), projectDtoResponse.getId());
        Assert.assertEquals(project.getName(), projectDtoResponse.getName());
        Assert.assertEquals(project.getDepartment().getId(), projectDtoResponse.getDepartment().getId());
        Assert.assertEquals(project.getDateStart(), projectDtoResponse.getDateStart());
        Assert.assertEquals(project.getDateFinal(), projectDtoResponse.getDateFinal());
        Assert.assertEquals(project.getValue(), projectDtoResponse.getValue());
//        Assert.assertEquals(project.getEmployeeList().get(0).getId(), projectDtoResponse.getEmployeeList().get(0).getId()); TODO
    }

    @Test
    public void updateShouldReturnEmployeeDtoResponse(){
        final Optional<Project> optionalProject = Optional.of(ProjectDataFactory.buildProject(1L));
        Mockito.when(projectDataService.findById(1L)).thenReturn(optionalProject);

        final ProjectDtoRequest projectDtoRequest = ProjectDataFactory.buildProjectDtoRequest(2L);

        final Department department = new Department();
        department.setId(projectDtoRequest.getIdDepartment());
        final Optional<Department> departmentProject = Optional.of(department);
        Mockito.when(departmentDataService.findById(projectDtoRequest.getIdDepartment())).thenReturn(departmentProject);

        final Project project = projectMapper.toProject(projectDtoRequest);
        project.setDepartment(departmentProject.get());
        Mockito.when(projectDataService.save(Mockito.any())).thenReturn(project);

        final ProjectDtoResponse projectDtoResponse = projectServiceImpl.update(1L, projectDtoRequest);

        Assert.assertEquals(project.getId(), projectDtoResponse.getId());
        Assert.assertEquals(project.getName(), projectDtoResponse.getName());
        Assert.assertEquals(project.getDepartment().getId(), projectDtoResponse.getDepartment().getId());
        Assert.assertEquals(project.getDateStart(), projectDtoResponse.getDateStart());
        Assert.assertEquals(project.getDateFinal(), projectDtoResponse.getDateFinal());
        Assert.assertEquals(project.getValue(), projectDtoResponse.getValue());
//        Assert.assertEquals(project.getEmployeeList().get(0).getId(), projectDtoResponse.getEmployeeList().get(0).getId()); TODO
    }

    @Test
    public void updateShouldReturnNull(){
        Mockito.when(projectDataService.findById(1L)).thenReturn(Optional.empty());

        final ProjectDtoResponse projectDtoResponse = projectServiceImpl.update(1L, null);

        Assert.assertNull(projectDtoResponse);
    }


    @Test
    public void deleteShouldReturnTrue(){
        Mockito.when(projectDataService.findById(1L)).thenReturn(Optional.of(new Project()));

        Assert.assertTrue(projectServiceImpl.delete(1L));
    }


    @Test
    public void deleteShouldReturnFalse(){
        Mockito.when(projectDataService.findById(1L)).thenReturn(Optional.empty());

        Assert.assertFalse(projectServiceImpl.delete(1L));
    }

    @Test
    public void findByIdShouldReturnProjectDtoResponse(){
        final Project project = ProjectDataFactory.buildProject(1L);
        Mockito.when(projectDataService.findById(1L)).thenReturn(Optional.of(project));

        final ProjectDtoResponse projectDtoResponse = projectServiceImpl.findById(1L);

        Assert.assertEquals(project.getId(), projectDtoResponse.getId());
        Assert.assertEquals(project.getName(), projectDtoResponse.getName());
        Assert.assertEquals(project.getDepartment().getId(), projectDtoResponse.getDepartment().getId());
        Assert.assertEquals(project.getDateStart(), projectDtoResponse.getDateStart());
        Assert.assertEquals(project.getDateFinal(), projectDtoResponse.getDateFinal());
        Assert.assertEquals(project.getValue(), projectDtoResponse.getValue());
        Assert.assertEquals(project.getEmployeeList().get(0).getId(), projectDtoResponse.getEmployeeList().get(0).getId());

    }

    @Test
    public void findByIdShouldReturnNull(){
        Mockito.when(projectDataService.findById(1L)).thenReturn(Optional.empty());

        Assert.assertNull(projectServiceImpl.findById(1L));
    }



}
