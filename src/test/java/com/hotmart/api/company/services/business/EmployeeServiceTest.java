package com.hotmart.api.company.services.business;

import com.hotmart.api.company.data.EmployeeDataFactory;
import com.hotmart.api.company.controller.form.EmployeeForm;
import com.hotmart.api.company.controller.vo.EmployeeVo;
import com.hotmart.api.company.model.entity.Address;
import com.hotmart.api.company.model.entity.Employee;
import com.hotmart.api.company.model.mapper.EmployeeMapper;
import com.hotmart.api.company.model.mapper.EmployeeMapperImpl;
import com.hotmart.api.company.repository.AddressRepository;
import com.hotmart.api.company.repository.EmployeeRepository;
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
public class EmployeeServiceTest {

    private EmployeeService employeeServiceImpl;

    private EmployeeMapper employeeMapper;

    @Mock
    private EmployeeRepository employeeRepository;

    @Mock
    private AddressRepository addressRepository;

    @Before
    public void init(){
        employeeMapper = new EmployeeMapperImpl();
        employeeServiceImpl = new EmployeeService(employeeMapper, employeeRepository, addressRepository);
    }

    @Test
    public void findAllShouldReturnListEmployeeDtoResponseWith3Elements(){
        final List<Employee> employeeList = new ArrayList<>();
        employeeList.add(EmployeeDataFactory.buildEmployee(1L));
        employeeList.add(EmployeeDataFactory.buildEmployee(2L));
        employeeList.add(EmployeeDataFactory.buildEmployee(3L));
        Mockito.when(employeeRepository.findAll()).thenReturn(employeeList);

        final List<EmployeeVo> employeeListDtoResponse = employeeServiceImpl.findAll();

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
        Mockito.when(employeeRepository.findAll()).thenReturn(new ArrayList<>());

        final List<EmployeeVo> employeeListDtoResponse = employeeServiceImpl.findAll();

        Assert.assertNull(employeeListDtoResponse);
    }


    @Test
    public void createShouldReturnEmployeeDtoResponse(){
        final EmployeeForm employeeForm = EmployeeDataFactory.buildEmployeeDtoRequest(1L);

        final Address address = new Address();
        address.setId(employeeForm.getIdAddress());
        final Optional<Address> addressEmployee = Optional.of(address);
        Mockito.when(addressRepository.findById(employeeForm.getIdAddress())).thenReturn(addressEmployee);

        final Employee supervisor = new Employee();
        supervisor.setId(employeeForm.getIdSupervisor());
        final Optional<Employee> supervisorEmployee = Optional.of(supervisor);
        Mockito.when(employeeRepository.findById(employeeForm.getIdSupervisor())).thenReturn(supervisorEmployee);

        final Employee employee = employeeMapper.toEmployee(employeeForm);
        employee.setAddress(addressEmployee.get());
        employee.setSupervisor(supervisorEmployee.get());
        Mockito.when(employeeRepository.save(Mockito.any())).thenReturn(employee);

        final EmployeeVo employeeVo = employeeServiceImpl.create(employeeForm);

        Assert.assertEquals(employeeForm.getName(), employeeVo.getName());
        Assert.assertEquals(employeeForm.getIdAddress(), employeeVo.getAddress().getId());
        Assert.assertEquals(employeeForm.getCpf(), employeeVo.getCpf());
        Assert.assertEquals(employeeForm.getDateOfBirth(), employeeVo.getDateOfBirth());
        Assert.assertEquals(employeeForm.getGender(), employeeVo.getGender().toString());
        Assert.assertEquals(employeeForm.getSalary(), employeeVo.getSalary());
        Assert.assertEquals(employeeForm.getIdSupervisor(), employeeVo.getSupervisor().getId());
//        Assert.assertEquals(employeeDtoRequest.getProjectList(), employeeDtoResponse.getProjectList()); TODO
    }

    @Test
    public void updateShouldReturnEmployeeDtoResponse(){
        final Optional<Employee> optionalEmployee = Optional.of(EmployeeDataFactory.buildEmployee(1L));
        Mockito.when(employeeRepository.findById(1L)).thenReturn(optionalEmployee);

        final EmployeeForm employeeForm = EmployeeDataFactory.buildEmployeeDtoRequest(2L);

        final Address address = new Address();
        address.setId(employeeForm.getIdAddress());
        final Optional<Address> addressEmployee = Optional.of(address);
        Mockito.when(addressRepository.findById(employeeForm.getIdAddress())).thenReturn(addressEmployee);

        final Employee supervisor = new Employee();
        supervisor.setId(employeeForm.getIdSupervisor());
        final Optional<Employee> supervisorEmployee = Optional.of(supervisor);
        Mockito.when(employeeRepository.findById(employeeForm.getIdSupervisor())).thenReturn(supervisorEmployee);

        final Employee employee = employeeMapper.toEmployee(employeeForm);
        employee.setAddress(addressEmployee.get());
        employee.setSupervisor(supervisorEmployee.get());
        Mockito.when(employeeRepository.save(Mockito.any())).thenReturn(employee);

        final EmployeeVo employeeVo = employeeServiceImpl.update(1L, employeeForm);

        Assert.assertEquals(employeeForm.getName(), employeeVo.getName());
        Assert.assertEquals(employeeForm.getIdAddress(), employeeVo.getAddress().getId());
        Assert.assertEquals(employeeForm.getCpf(), employeeVo.getCpf());
        Assert.assertEquals(employeeForm.getDateOfBirth(), employeeVo.getDateOfBirth());
        Assert.assertEquals(employeeForm.getGender(), employeeVo.getGender().toString());
        Assert.assertEquals(employeeForm.getSalary(), employeeVo.getSalary());
        Assert.assertEquals(employeeForm.getIdSupervisor(), employeeVo.getSupervisor().getId());
//        Assert.assertEquals(employeeDtoRequest.getProjectList(), employeeDtoResponse.getProjectList()); TODO
    }


    @Test
    public void updateShouldReturnNull(){
        Mockito.when(employeeRepository.findById(1L)).thenReturn(Optional.empty());

        final EmployeeVo employeeVo = employeeServiceImpl.update(1L, null);

        Assert.assertNull(employeeVo);
    }

    @Test
    public void deleteShouldReturnTrue(){
        Mockito.when(employeeRepository.findById(1L)).thenReturn(Optional.of(new Employee()));

        Assert.assertTrue(employeeServiceImpl.delete(1L));
    }


    @Test
    public void deleteShouldReturnFalse(){
        Mockito.when(employeeRepository.findById(1L)).thenReturn(Optional.empty());

        Assert.assertFalse(employeeServiceImpl.delete(1L));
    }

    @Test
    public void findByIdShouldReturnEmployeeDtoResponse(){
        final Employee employee = EmployeeDataFactory.buildEmployee(1L);
        Mockito.when(employeeRepository.findById(1L)).thenReturn(Optional.of(employee));

        final EmployeeVo employeeVo = employeeServiceImpl.findById(1L);

        Assert.assertEquals(employee.getName(), employeeVo.getName());
        Assert.assertEquals(employee.getAddress().getId(), employeeVo.getAddress().getId());
        Assert.assertEquals(employee.getCpf(), employeeVo.getCpf());
        Assert.assertEquals(employee.getDateOfBirth(), employeeVo.getDateOfBirth());
        Assert.assertEquals(employee.getGender(), employeeVo.getGender());
        Assert.assertEquals(employee.getSalary(), employeeVo.getSalary());
        Assert.assertEquals(employee.getSupervisor().getId(), employeeVo.getSupervisor().getId());
//        Assert.assertEquals(employee.getProjectList(), employeeDtoResponse.getProjectList()); TODO
    }

    @Test
    public void findByIdShouldReturnNull(){
        Mockito.when(employeeRepository.findById(1L)).thenReturn(Optional.empty());

        Assert.assertNull(employeeServiceImpl.findById(1L));
    }


}
