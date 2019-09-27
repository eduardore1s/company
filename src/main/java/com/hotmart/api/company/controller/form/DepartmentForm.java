package com.hotmart.api.company.controller.form;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
public class DepartmentForm {

    @NotNull
    private String name;

    @NotNull
    private Integer number;

//    private List<Long> idBudgets; TODO
}
