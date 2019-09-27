package com.hotmart.api.company.services.business;

import com.hotmart.api.company.controller.form.DepartmentForm;
import com.hotmart.api.company.controller.vo.DepartmentVo;
import com.hotmart.api.company.model.entity.Department;
import com.hotmart.api.company.model.exception.GenericErrorException;
import com.hotmart.api.company.model.exception.ResourceNotFoundException;
import com.hotmart.api.company.model.mapper.DepartmentMapper;
import com.hotmart.api.company.repository.DepartmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
        return new ArrayList<>();
    }

    public DepartmentVo create(DepartmentForm departmentForm) throws GenericErrorException {
        final Department department = departmentRepository.save(departmentMapper.toDepartment(departmentForm));

        if (department != null) {
            return departmentMapper.toDepartmentVo(department);
        }

        throw new GenericErrorException(null, "Ocorreu um erro ao processar a criação deste recurso.");
    }

    public DepartmentVo update(Long id, DepartmentForm departmentForm) throws ResourceNotFoundException {
        final Optional<Department> departmentOptional = departmentRepository.findById(id);

        if (departmentOptional.isPresent()){
            final Department department = departmentOptional.get();
            departmentMapper.updateDepartment(departmentForm, department);
            departmentRepository.save(department);

            return departmentMapper.toDepartmentVo(department);
        }
        throw new ResourceNotFoundException("id", "Nao existe Department para este id.");
    }

    public void delete(Long id) throws ResourceNotFoundException {
        final Optional<Department> departmentOptional = departmentRepository.findById(id);

        if (!departmentOptional.isPresent()){
            throw new ResourceNotFoundException("id", "Nao existe Department para este id.");
        }
        departmentRepository.delete(departmentOptional.get());
    }

    public DepartmentVo findById(Long id) throws ResourceNotFoundException {

        final Optional<Department> department = departmentRepository.findById(id);

        if (department.isPresent()){
            return departmentMapper.toDepartmentVo(department.get());
        }
        throw new ResourceNotFoundException("id", "Nao existe Department para este id.");
    }
}
