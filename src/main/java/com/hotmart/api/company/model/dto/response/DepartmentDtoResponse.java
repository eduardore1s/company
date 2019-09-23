package com.hotmart.api.company.model.dto.response;

import com.hotmart.api.company.model.entity.Budget;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class DepartmentDtoResponse {

    private Long id;

    private String name;

    private Integer number;

    private List<Budget> budget;
}