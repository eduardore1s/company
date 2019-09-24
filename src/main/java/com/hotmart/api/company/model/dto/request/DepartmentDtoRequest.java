package com.hotmart.api.company.model.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
public class DepartmentDtoRequest {

    @NotNull
    private String name;

    @NotNull
    private Integer number;

//    private List<Long> idBudgets;
}
