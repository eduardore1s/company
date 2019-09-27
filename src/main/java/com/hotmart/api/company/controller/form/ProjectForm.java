package com.hotmart.api.company.controller.form;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class ProjectForm {

    @NotNull
    private String name;

    @NotNull
    private Long idDepartment;

    @NotNull
    private BigDecimal value;

    @NotNull
    private LocalDate dateStart;

    @NotNull
    private LocalDate dateFinal;

//    private List<Long> idEmployees;
}
