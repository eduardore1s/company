package com.hotmart.api.company.services.business;

import com.hotmart.api.company.model.dto.request.EmployeeDtoRequest;
import com.hotmart.api.company.model.dto.response.EmployeeDtoResponse;
import com.hotmart.api.company.model.entity.Address;
import com.hotmart.api.company.model.entity.Employee;
import com.hotmart.api.company.model.mapper.EmployeeMapper;
import com.hotmart.api.company.repository.AddressRepository;
import com.hotmart.api.company.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EmployeeService {

    private final EmployeeMapper employeeMapper;

    private final EmployeeRepository employeeRepository;

    private final AddressRepository addressRepository;

    public List<EmployeeDtoResponse> findAll() {

        final List<Employee>  employeeList = employeeRepository.findAll();
        if (!employeeList.isEmpty()){
            return employeeList.stream().map(employeeMapper::toEmployeeDtoResponse).collect(Collectors.toList());
        }
        return null;
    }

    public EmployeeDtoResponse create(EmployeeDtoRequest employeeDtoRequest) {

        final Employee employee = employeeMapper.toEmployee(employeeDtoRequest);

        setAddressEmployee(employeeDtoRequest, employee);
        setSupervisorEmployee(employeeDtoRequest, employee);

        return employeeMapper.toEmployeeDtoResponse(employeeRepository.save(employee));
    }

    public EmployeeDtoResponse update(Long id, EmployeeDtoRequest employeeDtoRequest) {

        final Optional<Employee> employeeOptional = employeeRepository.findById(id);

        if (employeeOptional.isPresent()) {

            final Employee employee = employeeOptional.get();
            employeeMapper.updateEmployee(employeeDtoRequest, employee);

            setAddressEmployee(employeeDtoRequest, employee);
            setSupervisorEmployee(employeeDtoRequest, employee);

            return employeeMapper.toEmployeeDtoResponse(employeeRepository.save(employee));
        }
        return null;
    }

    public boolean delete(Long id) {
        final Optional<Employee> employeeOptional = employeeRepository.findById(id);

        if (employeeOptional.isPresent()){
            employeeRepository.delete(employeeOptional.get());
            return true;
        }

        return false;
    }

    public EmployeeDtoResponse findById(Long id) {

        final Optional<Employee> employee = employeeRepository.findById(id);

        if (employee.isPresent()){
            return employeeMapper.toEmployeeDtoResponse(employee.get());
        }

        return null;
    }

    public List<EmployeeDtoResponse> findByProjectListDepartmentId(Long idDepartment) {

        final List<Employee>  employeeList = employeeRepository.findByProjectListDepartmentId(idDepartment);
        if (!employeeList.isEmpty()){
            return employeeList.stream().map(employeeMapper::toEmployeeDtoResponse).collect(Collectors.toList());
        }
        return null;
    }

    public EmployeeDtoResponse findByName(String name) {

        final Optional<Employee> employee = employeeRepository.findByName(name);

        if (employee.isPresent()){
            return employeeMapper.toEmployeeDtoResponse(employee.get());
        }

        return null;
    }

    public List<EmployeeDtoResponse> findBySupervisorId(Long idSupervisor) {

        final List<Employee>  employeeList = employeeRepository.findBySupervisorId(idSupervisor);
        if (!employeeList.isEmpty()){
            return employeeList.stream().map(employeeMapper::toEmployeeDtoResponse).collect(Collectors.toList());
        }
        return null;
    }

    private void setAddressEmployee(EmployeeDtoRequest employeeDtoRequest, Employee employee) {
        if (employeeDtoRequest.getIdAddress() != null){
            final Optional<Address> addressEmployee = addressRepository.findById(employeeDtoRequest.getIdAddress());

            if (addressEmployee.isPresent()){
                employee.setAddress(addressEmployee.get());
            }
        }
    }

    private void setSupervisorEmployee(EmployeeDtoRequest employeeDtoRequest, Employee employee) {
        if (employeeDtoRequest.getIdSupervisor() != null) {
            final Optional<Employee> supervisorEmployee = employeeRepository.findById(employeeDtoRequest.getIdSupervisor());

            if (supervisorEmployee.isPresent()){
                employee.setSupervisor(supervisorEmployee.get());
            }
        }
    }
}
