package com.hotmart.api.company.services.business;

import com.hotmart.api.company.model.dto.request.EmployeeDtoRequest;
import com.hotmart.api.company.model.dto.response.EmployeeDtoResponse;
import com.hotmart.api.company.model.entity.Address;
import com.hotmart.api.company.model.entity.Employee;
import com.hotmart.api.company.model.mapper.EmployeeMapper;
import com.hotmart.api.company.services.data.AddressDataService;
import com.hotmart.api.company.services.data.EmployeeDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EmployeeServiceImpl implements EmployeeService{

    @Autowired
    private EmployeeMapper employeeMapper;

    @Autowired
    private EmployeeDataService employeeDataService;

    @Autowired
    private AddressDataService addressDataService;

    @Override
    public List<EmployeeDtoResponse> findAll() {

        final List<Employee>  employeeList = employeeDataService.findAll();
        if (!employeeList.isEmpty()){
            return employeeList.stream().map(employeeMapper::toEmployeeDtoResponse).collect(Collectors.toList());
        }
        return null;
    }

    @Override
    public EmployeeDtoResponse create(EmployeeDtoRequest employeeDtoRequest) {

        Employee employeeResponse = null;

        final Optional<Address> addressEmployee = addressDataService.findById(employeeDtoRequest.getIdAddress());
        final Optional<Employee> supervisorEmployee = employeeDataService.findById(employeeDtoRequest.getIdSupervisor());

        if (addressEmployee.isPresent() && supervisorEmployee.isPresent()){
            final Employee employee = employeeMapper.toEmployee(employeeDtoRequest);
            employee.setAddress(addressEmployee.get());
            employee.setSupervisor(supervisorEmployee.get());

            employeeResponse = employeeDataService.save(employee);
        }

        return employeeMapper.toEmployeeDtoResponse(employeeResponse);
    }

    @Override
    public EmployeeDtoResponse update(Long id, EmployeeDtoRequest employeeDtoRequest) {

        final Optional<Employee> employeeOptional = employeeDataService.findById(id);
        final Optional<Address> addressEmployee = addressDataService.findById(employeeDtoRequest.getIdAddress());
        final Optional<Employee> supervisorEmployee = employeeDataService.findById(employeeDtoRequest.getIdSupervisor());

        if (employeeOptional.isPresent() && addressEmployee.isPresent() && supervisorEmployee.isPresent()) {

            final Employee employee = employeeOptional.get();
            employeeMapper.updateEmployee(employeeDtoRequest, employee);
            employee.setAddress(addressEmployee.get());
            employee.setSupervisor(supervisorEmployee.get());

            employeeDataService.save(employee);

            return employeeMapper.toEmployeeDtoResponse(employee);
        }
        return null;
    }

    @Override
    public boolean delete(Long id) {
        final Optional<Employee> employeeOptional = employeeDataService.findById(id);

        if (employeeOptional.isPresent()){
            employeeDataService.delete(employeeOptional.get());
            return true;
        }

        return false;
    }

    @Override
    public EmployeeDtoResponse findById(Long id) {

        final Optional<Employee> employee = employeeDataService.findById(id);

        if (employee.isPresent()){
            return employeeMapper.toEmployeeDtoResponse(employee.get());
        }

        return null;
    }
}
