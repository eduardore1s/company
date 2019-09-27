package com.hotmart.api.company.model.vo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class ProjectVo {

    private Long id;

    private String name;

    private DepartmentVo department;

    private BigDecimal value;

    private LocalDate dateStart;

    private LocalDate dateFinal;

    private List<EmployeeVo> employeeList;
}
