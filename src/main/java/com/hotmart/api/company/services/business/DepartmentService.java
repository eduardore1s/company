package com.hotmart.api.company.services.business;

import com.hotmart.api.company.controller.form.DepartmentForm;
import com.hotmart.api.company.controller.vo.DepartmentVo;
import com.hotmart.api.company.model.entity.Department;
import com.hotmart.api.company.model.mapper.DepartmentMapper;
import com.hotmart.api.company.repository.DepartmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DepartmentService {

    private final DepartmentMapper departmentMapper;

    private final DepartmentRepository departmentRepository;

    public List<DepartmentVo> findAll() {
        
        final List<Department>  departmentList = departmentRepository.findAll();
        if (!departmentList.isEmpty()){
            return departmentList.stream().map(departmentMapper::toDepartmentVo).collect(Collectors.toList());
        }
        return null;
    }

    public DepartmentVo create(DepartmentForm departmentForm) {
        final Department department = departmentRepository.save(departmentMapper.toDepartment(departmentForm));
        return departmentMapper.toDepartmentVo(department);
    }

    public DepartmentVo update(Long id, DepartmentForm departmentForm) {
        final Optional<Department> departmentOptional = departmentRepository.findById(id);

        if (departmentOptional.isPresent()){
            final Department department = departmentOptional.get();
            departmentMapper.updateDepartment(departmentForm, department);
            departmentRepository.save(department);

            return departmentMapper.toDepartmentVo(department);
        }

        return null;
    }

    public boolean delete(Long id) {
        final Optional<Department> departmentOptional = departmentRepository.findById(id);

        if (departmentOptional.isPresent()){
            departmentRepository.delete(departmentOptional.get());
            return true;
        }

        return false;
    }

    public DepartmentVo findById(Long id) {

        final Optional<Department> department = departmentRepository.findById(id);

        if (department.isPresent()){
            return departmentMapper.toDepartmentVo(department.get());
        }

        return null;
    }
}
