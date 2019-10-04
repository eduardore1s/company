package com.hotmart.api.company.controller;

import com.hotmart.api.company.controller.form.BudgetForm;
import com.hotmart.api.company.controller.form.DepartmentForm;
import com.hotmart.api.company.controller.vo.BudgetVo;
import com.hotmart.api.company.controller.vo.DepartmentVo;
import com.hotmart.api.company.services.business.DepartmentService;
import com.hotmart.api.company.services.business.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("api/v1/departments")
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;

    @Autowired
    private EmployeeService employeeService;

    @GetMapping
    public ResponseEntity<?> getDepartments() {
        return ResponseEntity.ok(departmentService.findAll());
    }

    @PostMapping
    public ResponseEntity<DepartmentVo> postDepartment(@Valid @RequestBody DepartmentForm departmentForm,
                                                       UriComponentsBuilder uriBuilder) {
        final DepartmentVo departmentVo = departmentService.create(departmentForm);
        final URI uri = uriBuilder.path("api/v1/department/{id}").buildAndExpand(departmentVo.getId()).toUri();
        return ResponseEntity.created(uri).body(departmentVo);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getDepartment(@PathVariable Long id) {
        return ResponseEntity.ok(departmentService.findById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<DepartmentVo> putDepartment(@PathVariable Long id,
                                                      @Valid @RequestBody DepartmentForm departmentForm) {
        final DepartmentVo departmentVo = departmentService.update(id, departmentForm);
        return new ResponseEntity<>(departmentVo, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<DepartmentVo> deleteDepartment(@PathVariable Long id) {
        departmentService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("{id}/employees")
    public ResponseEntity<?> getEmployeesOfDepartment(@PathVariable Long id) {
        return ResponseEntity.ok(employeeService.findByProjectListDepartmentId(id));
    }

    @GetMapping("{id}/budgets")
    public ResponseEntity<?> getBudgetStatusOfDepartment(@PathVariable Long id) {
        return ResponseEntity.ok(departmentService.getStatusBudget());
    }

    @PostMapping("{id}/budgets")
    public ResponseEntity<BudgetVo> postBudgetDepartment(@PathVariable Long id, @Valid @RequestBody BudgetForm budgetForm) {
        final BudgetVo budgetVo = departmentService.createBudget(id, budgetForm);
        return new ResponseEntity<>(budgetVo, HttpStatus.CREATED);
    }

}
