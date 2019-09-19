package com.hotmart.api.company.repository;

import com.hotmart.api.company.model.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BudgetRepository extends JpaRepository<Address, Long> {
}
