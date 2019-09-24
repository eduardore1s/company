package com.hotmart.api.company.services.business;

import com.hotmart.api.company.model.dto.request.DepartmentDtoRequest;
import com.hotmart.api.company.model.dto.response.DepartmentDtoResponse;
import com.hotmart.api.company.model.entity.Department;
import com.hotmart.api.company.model.mapper.DepartmentMapper;
import com.hotmart.api.company.services.data.DepartmentDataService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DepartmentServiceImpl implements DepartmentService {

    private final DepartmentMapper departmentMapper;

    private final DepartmentDataService departmentDataService;

    @Override
    public List<DepartmentDtoResponse> findAll() {
        
        final List<Department>  departmentList = departmentDataService.findAll();
        if (!departmentList.isEmpty()){
            return departmentList.stream().map(departmentMapper::toDepartmentDtoResponse).collect(Collectors.toList());
        }
        return null;
    }

    @Override
    public DepartmentDtoResponse create(DepartmentDtoRequest departmentDtoRequest) {
        final Department department = departmentDataService.save(departmentMapper.toDepartment(departmentDtoRequest));
        return departmentMapper.toDepartmentDtoResponse(department);
    }

    @Override
    public DepartmentDtoResponse update(Long id, DepartmentDtoRequest departmentDtoRequest) {
        final Optional<Department> departmentOptional = departmentDataService.findById(id);

        if (departmentOptional.isPresent()){
            final Department department = departmentOptional.get();
            departmentMapper.updateDepartment(departmentDtoRequest, department);
            departmentDataService.save(department);

            return departmentMapper.toDepartmentDtoResponse(department);
        }

        return null;
    }

    @Override
    public boolean delete(Long id) {
        final Optional<Department> departmentOptional = departmentDataService.findById(id);

        if (departmentOptional.isPresent()){
            departmentDataService.delete(departmentOptional.get());
            return true;
        }

        return false;
    }

    @Override
    public DepartmentDtoResponse findById(Long id) {

        final Optional<Department> department = departmentDataService.findById(id);

        if (department.isPresent()){
            return departmentMapper.toDepartmentDtoResponse(department.get());
        }

        return null;
    }
}
