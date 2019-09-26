package com.hotmart.api.company.services.business;

import com.hotmart.api.company.model.dto.request.EmployeeDtoRequest;
import com.hotmart.api.company.model.dto.response.EmployeeDtoResponse;
import com.hotmart.api.company.model.entity.Address;
import com.hotmart.api.company.model.entity.Employee;
import com.hotmart.api.company.model.mapper.EmployeeMapper;
import com.hotmart.api.company.services.data.AddressDataService;
import com.hotmart.api.company.services.data.EmployeeDataService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService{

    private final EmployeeMapper employeeMapper;

    private final EmployeeDataService employeeDataService;

    private final AddressDataService addressDataService;

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

        final Employee employee = employeeMapper.toEmployee(employeeDtoRequest);

        setAddressEmployee(employeeDtoRequest, employee);
        setSupervisorEmployee(employeeDtoRequest, employee);

        return employeeMapper.toEmployeeDtoResponse(employeeDataService.save(employee));
    }

    @Override
    public EmployeeDtoResponse update(Long id, EmployeeDtoRequest employeeDtoRequest) {

        final Optional<Employee> employeeOptional = employeeDataService.findById(id);

        if (employeeOptional.isPresent()) {

            final Employee employee = employeeOptional.get();
            employeeMapper.updateEmployee(employeeDtoRequest, employee);

            setAddressEmployee(employeeDtoRequest, employee);
            setSupervisorEmployee(employeeDtoRequest, employee);

            return employeeMapper.toEmployeeDtoResponse(employeeDataService.save(employee));
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

    @Override
    public List<EmployeeDtoResponse> findByProjectListDepartmentId(Long idDepartment) {

        final List<Employee>  employeeList = employeeDataService.findByProjectListDepartmentId(idDepartment);
        if (!employeeList.isEmpty()){
            return employeeList.stream().map(employeeMapper::toEmployeeDtoResponse).collect(Collectors.toList());
        }
        return null;
    }

    @Override
    public EmployeeDtoResponse findByName(String name) {

        final Optional<Employee> employee = employeeDataService.findByName(name);

        if (employee.isPresent()){
            return employeeMapper.toEmployeeDtoResponse(employee.get());
        }

        return null;
    }


    private void setAddressEmployee(EmployeeDtoRequest employeeDtoRequest, Employee employee) {
        if (employeeDtoRequest.getIdAddress() != null){
            final Optional<Address> addressEmployee = addressDataService.findById(employeeDtoRequest.getIdAddress());

            if (addressEmployee.isPresent()){
                employee.setAddress(addressEmployee.get());
            }
        }
    }

    private void setSupervisorEmployee(EmployeeDtoRequest employeeDtoRequest, Employee employee) {
        if (employeeDtoRequest.getIdSupervisor() != null) {
            final Optional<Employee> supervisorEmployee = employeeDataService.findById(employeeDtoRequest.getIdSupervisor());

            if (supervisorEmployee.isPresent()){
                employee.setSupervisor(supervisorEmployee.get());
            }
        }
    }
}
