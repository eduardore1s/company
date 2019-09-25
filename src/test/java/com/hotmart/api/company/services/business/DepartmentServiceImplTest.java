package com.hotmart.api.company.services.business;

import com.hotmart.api.company.data.DepartmentDataFactory;
import com.hotmart.api.company.model.dto.request.DepartmentDtoRequest;
import com.hotmart.api.company.model.dto.response.DepartmentDtoResponse;
import com.hotmart.api.company.model.entity.Department;
import com.hotmart.api.company.model.mapper.DepartmentMapper;
import com.hotmart.api.company.model.mapper.DepartmentMapperImpl;
import com.hotmart.api.company.services.data.DepartmentDataService;
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
public class DepartmentServiceImplTest {

    private DepartmentServiceImpl departmentServiceImpl;

    private DepartmentMapper departmentMapper;

    @Mock
    private DepartmentDataService departmentDataService;

    @Before
    public void init(){
        departmentMapper = new DepartmentMapperImpl();
        departmentServiceImpl = new DepartmentServiceImpl(departmentMapper, departmentDataService);
    }

    @Test
    public void findAllShouldReturnListDepartmentDtoResponseWith3Elements(){
        final List<Department> departmentList = new ArrayList<>();
        departmentList.add(DepartmentDataFactory.buildDepartment(1L));
        departmentList.add(DepartmentDataFactory.buildDepartment(2L));
        departmentList.add(DepartmentDataFactory.buildDepartment(3L));
        Mockito.when(departmentDataService.findAll()).thenReturn(departmentList);

        final List<DepartmentDtoResponse> departmentListDtoResponse = departmentServiceImpl.findAll();

        Assert.assertTrue(departmentListDtoResponse.size() == 3);
        Assert.assertEquals(departmentList.get(0).getName(), departmentListDtoResponse.get(0).getName());
        Assert.assertEquals(departmentList.get(0).getNumber(), departmentListDtoResponse.get(0).getNumber());
        Assert.assertEquals(departmentList.get(0).getBudgets().get(0).getValue(), departmentListDtoResponse.get(0).getBudgets().get(0).getValue());

        Assert.assertEquals(departmentList.get(1).getName(), departmentListDtoResponse.get(1).getName());
        Assert.assertEquals(departmentList.get(2).getName(), departmentListDtoResponse.get(2).getName());
    }

    @Test
    public void findAllShouldReturnNull(){
        Mockito.when(departmentDataService.findAll()).thenReturn(new ArrayList<>());

        final List<DepartmentDtoResponse> departmentListDtoResponse = departmentServiceImpl.findAll();

        Assert.assertNull(departmentListDtoResponse);
    }

    @Test
    public void createShouldReturnDepartmentDtoResponse(){
        final DepartmentDtoRequest departmentDtoRequest = DepartmentDataFactory.buildDepartmentDtoRequest(1L);
        Mockito.when(departmentDataService.save(Mockito.any())).thenReturn(departmentMapper.toDepartment(departmentDtoRequest));

        final DepartmentDtoResponse departmentDtoResponse = departmentServiceImpl.create(departmentDtoRequest);

        Assert.assertEquals(departmentDtoRequest.getName(), departmentDtoResponse.getName());
        Assert.assertEquals(departmentDtoRequest.getNumber(), departmentDtoResponse.getNumber());
//        Assert.assertEquals(departmentDtoRequest.getIdBudgets().get(0), departmentDtoResponse.getBudgets().get(0).getId());
    }

    @Test
    public void updateShouldReturnDepartmentDtoResponse(){
        final Optional<Department> optionalDepartment = Optional.of(DepartmentDataFactory.buildDepartment(1L));
        Mockito.when(departmentDataService.findById(1L)).thenReturn(optionalDepartment);

        final DepartmentDtoRequest departmentDtoRequest = DepartmentDataFactory.buildDepartmentDtoRequest(2L);

        final DepartmentDtoResponse departmentDtoResponse = departmentServiceImpl.update(1L, departmentDtoRequest);

        Assert.assertEquals(departmentDtoRequest.getName(), departmentDtoResponse.getName());
        Assert.assertEquals(departmentDtoRequest.getNumber(), departmentDtoResponse.getNumber());
    }

    @Test
    public void updateShouldReturnNull(){
        Mockito.when(departmentDataService.findById(1L)).thenReturn(Optional.empty());

        final DepartmentDtoResponse departmentDtoResponse = departmentServiceImpl.update(1L, null);

        Assert.assertNull(departmentDtoResponse);
    }

    @Test
    public void deleteShouldReturnTrue(){
        Mockito.when(departmentDataService.findById(1L)).thenReturn(Optional.of(new Department()));

        Assert.assertTrue(departmentServiceImpl.delete(1L));
    }


    @Test
    public void deleteShouldReturnFalse(){
        Mockito.when(departmentDataService.findById(1L)).thenReturn(Optional.empty());

        Assert.assertFalse(departmentServiceImpl.delete(1L));
    }

    @Test
    public void findByIdShouldReturnDepartmentDtoResponse(){
        final Department department = DepartmentDataFactory.buildDepartment(1L);
        Mockito.when(departmentDataService.findById(1L)).thenReturn(Optional.of(department));

        final DepartmentDtoResponse departmentDtoResponse = departmentServiceImpl.findById(1L);

        Assert.assertEquals(department.getName(), departmentDtoResponse.getName());
        Assert.assertEquals(department.getNumber(), departmentDtoResponse.getNumber());
    }

    @Test
    public void findByIdShouldReturnNull(){
        Mockito.when(departmentDataService.findById(1L)).thenReturn(Optional.empty());

        Assert.assertNull(departmentServiceImpl.findById(1L));
    }

}
