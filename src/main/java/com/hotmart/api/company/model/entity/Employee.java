package com.hotmart.api.company.model.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String cpf;

    private LocalDate dateOfBirth;

    @Enumerated(EnumType.STRING)
    private EmployeeGender gender;

    @OneToOne
    private Address address;

    @OneToOne
    private Employee supervisor;

    private BigDecimal salary;

    @ManyToMany
    @JoinTable(name = "employee_project",
            joinColumns = { @JoinColumn(name = "employee_id") },
            inverseJoinColumns = { @JoinColumn(name = "project_list_id") })
    private List<Project> projectList;

}
