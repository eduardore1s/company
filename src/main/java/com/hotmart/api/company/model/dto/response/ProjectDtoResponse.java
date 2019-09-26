package com.hotmart.api.company.model.dto.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class ProjectDtoResponse {

    private Long id;

    private String name;

    private DepartmentDtoResponse department;

    private BigDecimal value;

    private LocalDate dateStart;

    private LocalDate dateFinal;

    private List<EmployeeDtoResponse> employeeList;
}
