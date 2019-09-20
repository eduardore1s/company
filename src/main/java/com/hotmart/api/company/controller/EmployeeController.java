package com.hotmart.api.company.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/employees")
public class EmployeeController {

//    @Autowired
//    private EmployeeDataService employeeDataService;
//
//    @GetMapping
//    public ResponseEntity<?> getEmployee(){
//
//        final List<EmployeeDto> employeeDtoList = employeeDataService.findAll();
//
//        if (employeeDtoList == null) {
//            return ResponseEntity.noContent().build();
//        }
//        return ResponseEntity.ok(employeeDtoList);
//    }
//
//    @PostMapping
//    public ResponseEntity<EmployeeDto> postEmployee(@Valid @RequestBody EmployeeDto employeeDto){
//
//        final EmployeeDto employeeDtoResponse = employeeDataService.create(employeeDto);
//        return new ResponseEntity<>(employeeDtoResponse, HttpStatus.CREATED);
//    }
//
//    @GetMapping("/{id}")
//    public ResponseEntity<?> getEmployee(@PathVariable Long id){
//
//        final EmployeeDto employeeDtoResponse = employeeDataService.findById(id);
//
//        if (employeeDtoResponse == null) {
//            return ResponseEntity.notFound().build();
//        }
//        return ResponseEntity.ok(employeeDtoResponse);
//    }
//
//    @PutMapping("/{id}")
//    public ResponseEntity<EmployeeDto> putEmployee(@PathVariable Long id, @RequestBody EmployeeDto ){
//
//        final EmployeeDto employeeDtoResponse = employeeDataService.update(id, employeeDto);
//
//        if (employeeDtoResponse == null){
//            return ResponseEntity.notFound().build();
//        }
//
//        return new ResponseEntity<>(employeeDtoResponse, HttpStatus.CREATED);
//    }
//
//    @DeleteMapping("/{id}")
//    public ResponseEntity<EmployeeDto> deleteEmployee(@PathVariable Long id){
//
//        if (!employeeDataService.delete(id)) {
//            return ResponseEntity.notFound().build();
//        }
//        return ResponseEntity.noContent().build();
//    }
}
