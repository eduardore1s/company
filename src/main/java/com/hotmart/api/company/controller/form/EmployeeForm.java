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
public class EmployeeForm {

    @NotNull
    private String name;

    @NotNull
    private String cpf;

    @NotNull
    private LocalDate dateOfBirth;

    @NotNull
    private String gender;

    @NotNull
    private Long idAddress;

    private Long idSupervisor;

    @NotNull
    private BigDecimal salary;

}
