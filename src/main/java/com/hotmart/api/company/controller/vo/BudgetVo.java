package com.hotmart.api.company.controller.vo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class BudgetVo {

    private Long id;

    private BigDecimal value;

    private LocalDate dateStart;

    private LocalDate dateFinal;
}
