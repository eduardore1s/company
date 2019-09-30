package com.hotmart.api.company.model.mapper;

import com.hotmart.api.company.controller.form.BudgetForm;
import com.hotmart.api.company.controller.vo.BudgetVo;
import com.hotmart.api.company.model.entity.Budget;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BudgetMapper {

    Budget toBudget(BudgetForm budgetForm);

    BudgetVo toBudgetVo(Budget budget);
}
