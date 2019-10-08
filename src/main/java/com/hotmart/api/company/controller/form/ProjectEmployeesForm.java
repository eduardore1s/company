package com.hotmart.api.company.controller.form;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class ProjectEmployeesForm {

    @NotNull
    private List<Long> idsEmployees;
}
