package com.hotmart.api.company.model.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class ProjectDtoRequest {

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

    @NotNull
    private List<Long> idEmployees;
}
