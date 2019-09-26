package com.hotmart.api.company.model.dto.response;

import com.hotmart.api.company.model.entity.EmployeeGender;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class EmployeeDtoResponse {

    private Long id;

    private String name;

    private String cpf;

    private LocalDate dateOfBirth;

    private EmployeeGender gender;

    private AddressDtoResponse address;

    private EmployeeDtoResponse supervisor;

    private BigDecimal salary;
}
