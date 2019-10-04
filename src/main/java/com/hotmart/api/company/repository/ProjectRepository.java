package com.hotmart.api.company.repository;

import com.hotmart.api.company.model.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface ProjectRepository extends JpaRepository<Project, Long> {

    List<Project> findByEmployeeListId(Long id);

    List<Project> findByDateStartAfter(LocalDate date);

    List<Project> findByDateStartBetweenAndDateFinalBetween(LocalDate firstDateStart, LocalDate lastDateStart,
                                                            LocalDate firstDateFinal, LocalDate lastDateFinal);

    List<Project> findByDateFinalBefore(LocalDate date);

    List<Project> findByDateStartBeforeAndDateFinalAfter(LocalDate firstDate, LocalDate lastDate);
}
