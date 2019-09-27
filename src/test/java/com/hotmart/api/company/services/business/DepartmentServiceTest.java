package com.hotmart.api.company.services.business;

import com.hotmart.api.company.data.DepartmentDataFactory;
import com.hotmart.api.company.controller.form.DepartmentForm;
import com.hotmart.api.company.controller.vo.DepartmentVo;
import com.hotmart.api.company.model.entity.Department;
import com.hotmart.api.company.model.exception.GenericErrorException;
import com.hotmart.api.company.model.exception.ResourceNotFoundException;
import com.hotmart.api.company.model.mapper.DepartmentMapper;
import com.hotmart.api.company.model.mapper.DepartmentMapperImpl;
import com.hotmart.api.company.repository.DepartmentRepository;
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
public class DepartmentServiceTest {

    private DepartmentService departmentService;

    private DepartmentMapper departmentMapper;

    @Mock
    private DepartmentRepository departmentRepository;

    @Before
    public void init(){
        departmentMapper = new DepartmentMapperImpl();
        departmentService = new DepartmentService(departmentMapper, departmentRepository);
    }

    @Test
    public void findAllShouldReturnListDepartmentDtoResponseWith3Elements(){
        final List<Department> departmentList = new ArrayList<>();
        departmentList.add(DepartmentDataFactory.buildDepartment(1L));
        departmentList.add(DepartmentDataFactory.buildDepartment(2L));
        departmentList.add(DepartmentDataFactory.buildDepartment(3L));
        Mockito.when(departmentRepository.findAll()).thenReturn(departmentList);

        final List<DepartmentVo> departmentListDtoResponse = departmentService.findAll();

        Assert.assertTrue(departmentListDtoResponse.size() == 3);
        Assert.assertEquals(departmentList.get(0).getName(), departmentListDtoResponse.get(0).getName());
        Assert.assertEquals(departmentList.get(0).getNumber(), departmentListDtoResponse.get(0).getNumber());
        Assert.assertEquals(departmentList.get(0).getBudgets().get(0).getValue(), departmentListDtoResponse.get(0).getBudgets().get(0).getValue());

        Assert.assertEquals(departmentList.get(1).getName(), departmentListDtoResponse.get(1).getName());
        Assert.assertEquals(departmentList.get(2).getName(), departmentListDtoResponse.get(2).getName());
    }

    @Test
    public void findAllShouldReturnListEmpty(){
        Mockito.when(departmentRepository.findAll()).thenReturn(new ArrayList<>());

        final List<DepartmentVo> departmentListDtoResponse = departmentService.findAll();

        Assert.assertNotNull(departmentListDtoResponse);
        Assert.assertTrue(departmentListDtoResponse.isEmpty());
    }

    @Test
    public void createShouldReturnDepartmentDtoResponse() throws GenericErrorException {
        final DepartmentForm departmentForm = DepartmentDataFactory.buildDepartmentDtoRequest(1L);
        Mockito.when(departmentRepository.save(Mockito.any())).thenReturn(departmentMapper.toDepartment(departmentForm));

        final DepartmentVo departmentVo = departmentService.create(departmentForm);

        Assert.assertEquals(departmentForm.getName(), departmentVo.getName());
        Assert.assertEquals(departmentForm.getNumber(), departmentVo.getNumber());
//        Assert.assertEquals(departmentDtoRequest.getIdBudgets().get(0), departmentDtoResponse.getBudgets().get(0).getId());
    }

    @Test
    public void updateShouldReturnDepartmentDtoResponse() throws ResourceNotFoundException {
        final Optional<Department> optionalDepartment = Optional.of(DepartmentDataFactory.buildDepartment(1L));
        Mockito.when(departmentRepository.findById(1L)).thenReturn(optionalDepartment);

        final DepartmentForm departmentForm = DepartmentDataFactory.buildDepartmentDtoRequest(2L);

        final DepartmentVo departmentVo = departmentService.update(1L, departmentForm);

        Assert.assertEquals(departmentForm.getName(), departmentVo.getName());
        Assert.assertEquals(departmentForm.getNumber(), departmentVo.getNumber());
    }

    @Test(expected = ResourceNotFoundException.class)
    public void updateShouldReturnNotFoundException() throws ResourceNotFoundException {
        Mockito.when(departmentRepository.findById(1L)).thenReturn(Optional.empty());

        departmentService.update(1L, null);
    }


    @Test(expected = ResourceNotFoundException.class)
    public void deleteShouldReturnNotFoundException() throws ResourceNotFoundException {
        Mockito.when(departmentRepository.findById(1L)).thenReturn(Optional.empty());

        departmentService.delete(1L);
    }

    @Test
    public void findByIdShouldReturnDepartmentDtoResponse() throws ResourceNotFoundException {
        final Department department = DepartmentDataFactory.buildDepartment(1L);
        Mockito.when(departmentRepository.findById(1L)).thenReturn(Optional.of(department));

        final DepartmentVo departmentVo = departmentService.findById(1L);

        Assert.assertEquals(department.getName(), departmentVo.getName());
        Assert.assertEquals(department.getNumber(), departmentVo.getNumber());
    }

    @Test(expected = ResourceNotFoundException.class)
    public void findByIdShouldReturnResourceNotFound() throws ResourceNotFoundException {
        Mockito.when(departmentRepository.findById(1L)).thenReturn(Optional.empty());

        departmentService.findById(1L);
    }

}
