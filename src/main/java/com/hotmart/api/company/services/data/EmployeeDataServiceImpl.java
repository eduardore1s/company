package com.hotmart.api.company.services.data;

import com.hotmart.api.company.model.entity.Employee;
import com.hotmart.api.company.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeDataServiceImpl implements EmployeeDataService{

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public List<Employee> findAll() {
        return employeeRepository.findAll();
    }

    @Override
    public Employee save(Employee employee) {
        return employeeRepository.save(employee);
    }

    @Override
    public void delete(Employee employee) {
        employeeRepository.delete(employee);
    }

    @Override
    public Optional<Employee> findById(Long id) {
        return employeeRepository.findById(id);
    }

    @Override
    public List<Employee> findByProjectListDepartmentId(Long idDepartment) {
        return employeeRepository.findByProjectListDepartmentId(idDepartment);
    }

    @Override
    public Optional<Employee> findByName(String name) {
        return employeeRepository.findByName(name);
    }
}
