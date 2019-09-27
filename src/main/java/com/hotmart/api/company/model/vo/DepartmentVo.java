package com.hotmart.api.company.model.vo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class DepartmentVo {

    private Long id;

    private String name;

    private Integer number;

    private List<BudgetVo> budgets;
}
