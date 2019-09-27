package com.hotmart.api.company.controller.vo;

import com.hotmart.api.company.model.entity.EmployeeGender;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class EmployeeVo {

    private Long id;

    private String name;

    private String cpf;

    private LocalDate dateOfBirth;

    private EmployeeGender gender;

    private AddressVo address;

    private EmployeeVo supervisor;

    private BigDecimal salary;
}
