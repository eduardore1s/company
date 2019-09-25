package com.hotmart.api.company.services.business;

import com.hotmart.api.company.data.EmployeeDataFactory;
import com.hotmart.api.company.model.dto.request.EmployeeDtoRequest;
import com.hotmart.api.company.model.dto.response.EmployeeDtoResponse;
import com.hotmart.api.company.model.entity.Address;
import com.hotmart.api.company.model.entity.Employee;
import com.hotmart.api.company.model.mapper.EmployeeMapper;
import com.hotmart.api.company.model.mapper.EmployeeMapperImpl;
import com.hotmart.api.company.services.data.AddressDataService;
import com.hotmart.api.company.services.data.EmployeeDataService;
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
public class EmployeeServiceImplTest {

    private EmployeeServiceImpl employeeServiceImpl;

    private EmployeeMapper employeeMapper;

    @Mock
    private EmployeeDataService employeeDataService;

    @Mock
    private AddressDataService addressDataService;

    @Before
    public void init(){
        employeeMapper = new EmployeeMapperImpl();
        employeeServiceImpl = new EmployeeServiceImpl(employeeMapper, employeeDataService, addressDataService);
    }

    @Test
    public void findAllShouldReturnListEmployeeDtoResponseWith3Elements(){
        final List<Employee> employeeList = new ArrayList<>();
        employeeList.add(EmployeeDataFactory.buildEmployee(1L));
        employeeList.add(EmployeeDataFactory.buildEmployee(2L));
        employeeList.add(EmployeeDataFactory.buildEmployee(3L));
        Mockito.when(employeeDataService.findAll()).thenReturn(employeeList);

        final List<EmployeeDtoResponse> employeeListDtoResponse = employeeServiceImpl.findAll();

        Assert.assertTrue(employeeListDtoResponse.size() == 3);
        Assert.assertEquals(employeeList.get(0).getName(), employeeListDtoResponse.get(0).getName());
        Assert.assertEquals(employeeList.get(0).getAddress().getId(), employeeListDtoResponse.get(0).getAddress().getId());
        Assert.assertEquals(employeeList.get(0).getCpf(), employeeListDtoResponse.get(0).getCpf());
        Assert.assertEquals(employeeList.get(0).getDateOfBirth(), employeeListDtoResponse.get(0).getDateOfBirth());
        Assert.assertEquals(employeeList.get(0).getGender(), employeeListDtoResponse.get(0).getGender());
        Assert.assertEquals(employeeList.get(0).getSalary(), employeeListDtoResponse.get(0).getSalary());
        Assert.assertEquals(employeeList.get(0).getSupervisor().getId(), employeeListDtoResponse.get(0).getSupervisor().getId());
//        Assert.assertEquals(employeeList.get(0).getProjectList().get(0).getName(), employeeListDtoResponse.get(0).getProjectList().get(0).getName()); TODO

        Assert.assertEquals(employeeList.get(1).getName(), employeeList.get(1).getName());
        Assert.assertEquals(employeeList.get(2).getName(), employeeList.get(2).getName());
    }

    @Test
    public void findAllShouldReturnNull(){
        Mockito.when(employeeDataService.findAll()).thenReturn(new ArrayList<>());

        final List<EmployeeDtoResponse> employeeListDtoResponse = employeeServiceImpl.findAll();

        Assert.assertNull(employeeListDtoResponse);
    }


    @Test
    public void createShouldReturnEmployeeDtoResponse(){
        final EmployeeDtoRequest employeeDtoRequest = EmployeeDataFactory.buildEmployeeDtoRequest(1L);

        final Address address = new Address();
        address.setId(employeeDtoRequest.getIdAddress());
        final Optional<Address> addressEmployee = Optional.of(address);
        Mockito.when(addressDataService.findById(employeeDtoRequest.getIdAddress())).thenReturn(addressEmployee);

        final Employee supervisor = new Employee();
        supervisor.setId(employeeDtoRequest.getIdSupervisor());
        final Optional<Employee> supervisorEmployee = Optional.of(supervisor);
        Mockito.when(employeeDataService.findById(employeeDtoRequest.getIdSupervisor())).thenReturn(supervisorEmployee);

        final Employee employee = employeeMapper.toEmployee(employeeDtoRequest);
        employee.setAddress(addressEmployee.get());
        employee.setSupervisor(supervisorEmployee.get());
        Mockito.when(employeeDataService.save(Mockito.any())).thenReturn(employee);

        final EmployeeDtoResponse employeeDtoResponse = employeeServiceImpl.create(employeeDtoRequest);

        Assert.assertEquals(employeeDtoRequest.getName(), employeeDtoResponse.getName());
        Assert.assertEquals(employeeDtoRequest.getIdAddress(), employeeDtoResponse.getAddress().getId());
        Assert.assertEquals(employeeDtoRequest.getCpf(), employeeDtoResponse.getCpf());
        Assert.assertEquals(employeeDtoRequest.getDateOfBirth(), employeeDtoResponse.getDateOfBirth());
        Assert.assertEquals(employeeDtoRequest.getGender(), employeeDtoResponse.getGender().toString());
        Assert.assertEquals(employeeDtoRequest.getSalary(), employeeDtoResponse.getSalary());
        Assert.assertEquals(employeeDtoRequest.getIdSupervisor(), employeeDtoResponse.getSupervisor().getId());
//        Assert.assertEquals(employeeDtoRequest.getProjectList(), employeeDtoResponse.getProjectList()); TODO
    }

    @Test
    public void updateShouldReturnEmployeeDtoResponse(){
        final Optional<Employee> optionalEmployee = Optional.of(EmployeeDataFactory.buildEmployee(1L));
        Mockito.when(employeeDataService.findById(1L)).thenReturn(optionalEmployee);

        final EmployeeDtoRequest employeeDtoRequest = EmployeeDataFactory.buildEmployeeDtoRequest(2L);

        final Address address = new Address();
        address.setId(employeeDtoRequest.getIdAddress());
        final Optional<Address> addressEmployee = Optional.of(address);
        Mockito.when(addressDataService.findById(employeeDtoRequest.getIdAddress())).thenReturn(addressEmployee);

        final Employee supervisor = new Employee();
        supervisor.setId(employeeDtoRequest.getIdSupervisor());
        final Optional<Employee> supervisorEmployee = Optional.of(supervisor);
        Mockito.when(employeeDataService.findById(employeeDtoRequest.getIdSupervisor())).thenReturn(supervisorEmployee);

        final Employee employee = employeeMapper.toEmployee(employeeDtoRequest);
        employee.setAddress(addressEmployee.get());
        employee.setSupervisor(supervisorEmployee.get());
        Mockito.when(employeeDataService.save(Mockito.any())).thenReturn(employee);

        final EmployeeDtoResponse employeeDtoResponse = employeeServiceImpl.update(1L, employeeDtoRequest);

        Assert.assertEquals(employeeDtoRequest.getName(), employeeDtoResponse.getName());
        Assert.assertEquals(employeeDtoRequest.getIdAddress(), employeeDtoResponse.getAddress().getId());
        Assert.assertEquals(employeeDtoRequest.getCpf(), employeeDtoResponse.getCpf());
        Assert.assertEquals(employeeDtoRequest.getDateOfBirth(), employeeDtoResponse.getDateOfBirth());
        Assert.assertEquals(employeeDtoRequest.getGender(), employeeDtoResponse.getGender().toString());
        Assert.assertEquals(employeeDtoRequest.getSalary(), employeeDtoResponse.getSalary());
        Assert.assertEquals(employeeDtoRequest.getIdSupervisor(), employeeDtoResponse.getSupervisor().getId());
//        Assert.assertEquals(employeeDtoRequest.getProjectList(), employeeDtoResponse.getProjectList()); TODO
    }


    @Test
    public void updateShouldReturnNull(){
        Mockito.when(employeeDataService.findById(1L)).thenReturn(Optional.empty());

        final EmployeeDtoResponse employeeDtoResponse = employeeServiceImpl.update(1L, null);

        Assert.assertNull(employeeDtoResponse);
    }

    @Test
    public void deleteShouldReturnTrue(){
        Mockito.when(employeeDataService.findById(1L)).thenReturn(Optional.of(new Employee()));

        Assert.assertTrue(employeeServiceImpl.delete(1L));
    }


    @Test
    public void deleteShouldReturnFalse(){
        Mockito.when(employeeDataService.findById(1L)).thenReturn(Optional.empty());

        Assert.assertFalse(employeeServiceImpl.delete(1L));
    }

    @Test
    public void findByIdShouldReturnEmployeeDtoResponse(){
        final Employee employee = EmployeeDataFactory.buildEmployee(1L);
        Mockito.when(employeeDataService.findById(1L)).thenReturn(Optional.of(employee));

        final EmployeeDtoResponse employeeDtoResponse = employeeServiceImpl.findById(1L);

        Assert.assertEquals(employee.getName(), employeeDtoResponse.getName());
        Assert.assertEquals(employee.getAddress().getId(), employeeDtoResponse.getAddress().getId());
        Assert.assertEquals(employee.getCpf(), employeeDtoResponse.getCpf());
        Assert.assertEquals(employee.getDateOfBirth(), employeeDtoResponse.getDateOfBirth());
        Assert.assertEquals(employee.getGender(), employeeDtoResponse.getGender());
        Assert.assertEquals(employee.getSalary(), employeeDtoResponse.getSalary());
        Assert.assertEquals(employee.getSupervisor().getId(), employeeDtoResponse.getSupervisor().getId());
//        Assert.assertEquals(employee.getProjectList(), employeeDtoResponse.getProjectList()); TODO
    }

    @Test
    public void findByIdShouldReturnNull(){
        Mockito.when(employeeDataService.findById(1L)).thenReturn(Optional.empty());

        Assert.assertNull(employeeServiceImpl.findById(1L));
    }


}
