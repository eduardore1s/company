package com.hotmart.api.company.model.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@Builder
@Entity
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    @OneToOne
    private Department department;

    private BigDecimal value;

    private LocalDate dateStart;

    private LocalDate dateFinal;

    @ManyToMany
    private List<Employee> employeeList;

}
