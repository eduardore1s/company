package com.hotmart.api.company.repository;

import com.hotmart.api.company.model.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProjectRepository extends JpaRepository<Project, Long> {

    List<Project> findByEmployeeListId(Long id);
}
