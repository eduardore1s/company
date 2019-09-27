package com.hotmart.api.company.services.business;

import com.hotmart.api.company.model.dto.request.DepartmentDtoRequest;
import com.hotmart.api.company.model.dto.response.DepartmentDtoResponse;
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

    public List<DepartmentDtoResponse> findAll() {
        
        final List<Department>  departmentList = departmentRepository.findAll();
        if (!departmentList.isEmpty()){
            return departmentList.stream().map(departmentMapper::toDepartmentDtoResponse).collect(Collectors.toList());
        }
        return null;
    }

    public DepartmentDtoResponse create(DepartmentDtoRequest departmentDtoRequest) {
        final Department department = departmentRepository.save(departmentMapper.toDepartment(departmentDtoRequest));
        return departmentMapper.toDepartmentDtoResponse(department);
    }

    public DepartmentDtoResponse update(Long id, DepartmentDtoRequest departmentDtoRequest) {
        final Optional<Department> departmentOptional = departmentRepository.findById(id);

        if (departmentOptional.isPresent()){
            final Department department = departmentOptional.get();
            departmentMapper.updateDepartment(departmentDtoRequest, department);
            departmentRepository.save(department);

            return departmentMapper.toDepartmentDtoResponse(department);
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

    public DepartmentDtoResponse findById(Long id) {

        final Optional<Department> department = departmentRepository.findById(id);

        if (department.isPresent()){
            return departmentMapper.toDepartmentDtoResponse(department.get());
        }

        return null;
    }
}
