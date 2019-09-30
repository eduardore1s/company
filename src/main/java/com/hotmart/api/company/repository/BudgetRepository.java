package com.hotmart.api.company.repository;

import com.hotmart.api.company.model.entity.Budget;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BudgetRepository extends JpaRepository<Budget, Long> {

    Budget findFirstByOrderByCreatedDateDesc();
}
