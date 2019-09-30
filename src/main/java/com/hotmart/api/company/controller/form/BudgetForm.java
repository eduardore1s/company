package com.hotmart.api.company.controller.form;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class BudgetForm {

    private BigDecimal value;

    private LocalDate dateStart;

    private LocalDate dateFinal;

    private LocalDate createdDate;
}
