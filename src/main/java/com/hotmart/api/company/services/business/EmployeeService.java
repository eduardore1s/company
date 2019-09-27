package com.hotmart.api.company.services.business;

import com.hotmart.api.company.controller.form.EmployeeForm;
import com.hotmart.api.company.controller.vo.EmployeeVo;
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

    public List<EmployeeVo> findAll() {

        final List<Employee>  employeeList = employeeRepository.findAll();
        if (!employeeList.isEmpty()){
            return employeeList.stream().map(employeeMapper::toEmployeeVo).collect(Collectors.toList());
        }
        return null;
    }

    public EmployeeVo create(EmployeeForm employeeForm) {

        final Employee employee = employeeMapper.toEmployee(employeeForm);

        setAddressEmployee(employeeForm, employee);
        setSupervisorEmployee(employeeForm, employee);

        return employeeMapper.toEmployeeVo(employeeRepository.save(employee));
    }

    public EmployeeVo update(Long id, EmployeeForm employeeForm) {

        final Optional<Employee> employeeOptional = employeeRepository.findById(id);

        if (employeeOptional.isPresent()) {

            final Employee employee = employeeOptional.get();
            employeeMapper.updateEmployee(employeeForm, employee);

            setAddressEmployee(employeeForm, employee);
            setSupervisorEmployee(employeeForm, employee);

            return employeeMapper.toEmployeeVo(employeeRepository.save(employee));
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

    public EmployeeVo findById(Long id) {

        final Optional<Employee> employee = employeeRepository.findById(id);

        if (employee.isPresent()){
            return employeeMapper.toEmployeeVo(employee.get());
        }

        return null;
    }

    public List<EmployeeVo> findByProjectListDepartmentId(Long idDepartment) {

        final List<Employee>  employeeList = employeeRepository.findByProjectListDepartmentId(idDepartment);
        if (!employeeList.isEmpty()){
            return employeeList.stream().map(employeeMapper::toEmployeeVo).collect(Collectors.toList());
        }
        return null;
    }

    public EmployeeVo findByName(String name) {

        final Optional<Employee> employee = employeeRepository.findByName(name);

        if (employee.isPresent()){
            return employeeMapper.toEmployeeVo(employee.get());
        }

        return null;
    }

    public List<EmployeeVo> findBySupervisorId(Long idSupervisor) {

        final List<Employee>  employeeList = employeeRepository.findBySupervisorId(idSupervisor);
        if (!employeeList.isEmpty()){
            return employeeList.stream().map(employeeMapper::toEmployeeVo).collect(Collectors.toList());
        }
        return null;
    }

    private void setAddressEmployee(EmployeeForm employeeForm, Employee employee) {
        if (employeeForm.getIdAddress() != null){
            final Optional<Address> addressEmployee = addressRepository.findById(employeeForm.getIdAddress());

            if (addressEmployee.isPresent()){
                employee.setAddress(addressEmployee.get());
            }
        }
    }

    private void setSupervisorEmployee(EmployeeForm employeeForm, Employee employee) {
        if (employeeForm.getIdSupervisor() != null) {
            final Optional<Employee> supervisorEmployee = employeeRepository.findById(employeeForm.getIdSupervisor());

            if (supervisorEmployee.isPresent()){
                employee.setSupervisor(supervisorEmployee.get());
            }
        }
    }
}
