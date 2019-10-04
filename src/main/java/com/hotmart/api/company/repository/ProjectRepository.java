package com.hotmart.api.company.repository;

import com.hotmart.api.company.model.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface ProjectRepository extends JpaRepository<Project, Long> {

    List<Project> findByEmployeeListId(Long id);

    List<Project> findByDepartmentIdAndDateStartAfter(Long id, LocalDate date);

    List<Project> findByDepartmentIdAndDateStartBetweenAndDateFinalBetween(Long id, LocalDate firstDateStart, LocalDate lastDateStart,
                                                                           LocalDate firstDateFinal, LocalDate lastDateFinal);

    List<Project> findByDepartmentIdAndDateFinalBefore(Long id, LocalDate date);

    List<Project> findByDepartmentIdAndDateStartBeforeAndDateFinalAfter(Long id, LocalDate firstDate, LocalDate lastDate);
}
