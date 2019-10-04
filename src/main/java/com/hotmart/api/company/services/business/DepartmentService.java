package com.hotmart.api.company.services.business;

import com.hotmart.api.company.controller.form.BudgetForm;
import com.hotmart.api.company.controller.form.DepartmentForm;
import com.hotmart.api.company.controller.vo.BudgetVo;
import com.hotmart.api.company.controller.vo.DepartmentVo;
import com.hotmart.api.company.controller.vo.StatusBudgetVo;
import com.hotmart.api.company.model.entity.Budget;
import com.hotmart.api.company.model.entity.Department;
import com.hotmart.api.company.model.entity.Employee;
import com.hotmart.api.company.model.entity.Project;
import com.hotmart.api.company.model.exception.GenericErrorException;
import com.hotmart.api.company.model.exception.ResourceNotFoundException;
import com.hotmart.api.company.model.mapper.BudgetMapper;
import com.hotmart.api.company.model.mapper.DepartmentMapper;
import com.hotmart.api.company.repository.BudgetRepository;
import com.hotmart.api.company.repository.DepartmentRepository;
import com.hotmart.api.company.repository.ProjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DepartmentService {

    private static final String VERDE = "VERDE";
    private static final String AMARELO = "AMARELO";
    private static final String VERMELHO = "VERMELHO";

    private static final String PROJECTS_THAT_START_AT_BUDGET = "PROJECTS_THAT_START_AT_BUDGET";
    private static final String PROJECTS_THAT_END_AT_BUDGET = "PROJECTS_THAT_END_AT_BUDGET";
    private static final String PROJECTS_THAT_START_AND_THAT_END_AT_BUDGET = "PROJECTS_THAT_START_AND_THAT_END_AT_BUDGET";
    private static final String PROJECTS_THAT_START_AND_THAT_END_OFF_BUDGET = "PROJECTS_THAT_START_AND_THAT_END_OFF_BUDGET";
    private static final BigDecimal DAYS_OF_MONTH = new BigDecimal(30);

    private final DepartmentMapper departmentMapper;

    private final BudgetMapper budgetMapper;

    private final DepartmentRepository departmentRepository;

    private final BudgetRepository budgetRepository;

    private final ProjectRepository projectRepository;

    public List<DepartmentVo> findAll() {

        final List<Department> departmentList = departmentRepository.findAll();
        if (!departmentList.isEmpty()) {
            return departmentList.stream().map(departmentMapper::toDepartmentVo).collect(Collectors.toList());
        }
        return new ArrayList<>();
    }

    public DepartmentVo create(DepartmentForm departmentForm) throws GenericErrorException {
        final Department department = departmentRepository.save(departmentMapper.toDepartment(departmentForm));

        if (department != null) {
            return departmentMapper.toDepartmentVo(department);
        }

        throw new GenericErrorException(null, "Ocorreu um erro ao processar a criação deste recurso.");
    }

    public DepartmentVo update(Long id, DepartmentForm departmentForm) throws ResourceNotFoundException {
        final Optional<Department> departmentOptional = departmentRepository.findById(id);

        if (departmentOptional.isPresent()) {
            final Department department = departmentOptional.get();
            departmentMapper.updateDepartment(departmentForm, department);
            departmentRepository.save(department);

            return departmentMapper.toDepartmentVo(department);
        }
        throw new ResourceNotFoundException("id", "Nao existe Department para este id.");
    }

    public void delete(Long id) throws ResourceNotFoundException {
        final Optional<Department> departmentOptional = departmentRepository.findById(id);

        if (!departmentOptional.isPresent()) {
            throw new ResourceNotFoundException("id", "Nao existe Department para este id.");
        }
        departmentRepository.delete(departmentOptional.get());
    }

    public DepartmentVo findById(Long id) throws ResourceNotFoundException {

        final Optional<Department> department = departmentRepository.findById(id);

        if (department.isPresent()) {
            return departmentMapper.toDepartmentVo(department.get());
        }
        throw new ResourceNotFoundException("id", "Nao existe Department para este id.");
    }

    public BudgetVo createBudget(Long idDepartment, BudgetForm budgetForm) throws GenericErrorException {

        final Optional<Department> departmentOptional = departmentRepository.findById(idDepartment);

        if (departmentOptional.isPresent()) {
            final Department department = departmentOptional.get();
            final Budget budget = budgetMapper.toBudget(budgetForm);
            budget.setCreatedDate(LocalDate.now());
            budgetRepository.save(budget);
            department.getBudgets().add(budget);
            departmentRepository.save(department);

            return budgetMapper.toBudgetVo(budget);
        }
        throw new ResourceNotFoundException("id", "Nao existe Department para este id.");
    }


    public StatusBudgetVo getStatusBudget() {

        final Budget currentBudget = budgetRepository.findFirstByOrderByCreatedDateDesc();

        final List<Project> projectsProcessed = new ArrayList<>();

        final BigDecimal totalProjectsBudget =
                calcTotalProjectsThatStartAndThatEndAtBudget(currentBudget, projectsProcessed)
                        .add(calcTotalProjectsThatEndAtBudget(currentBudget, projectsProcessed))
                        .add(calcTotalProjectsThatStartAtBudget(currentBudget, projectsProcessed))
                        .add(calcTotalProjectsThatStartAndEndOffBudget(currentBudget, projectsProcessed));

        return getStatusBudgetVo(currentBudget, totalProjectsBudget);

    }

    private StatusBudgetVo getStatusBudgetVo(Budget currentBudget, BigDecimal totalProjectsBudget) {

        if (statusIsVerde(currentBudget, totalProjectsBudget)) {
            return StatusBudgetVo.builder().status(VERDE).build();
        }
        if (statusIsAmarelo(currentBudget, totalProjectsBudget)) {
            return StatusBudgetVo.builder().status(AMARELO).build();
        }
        return StatusBudgetVo.builder().status(VERMELHO).build();
    }

    private boolean statusIsVerde(Budget currentBudget, BigDecimal totalProjectsBudget) {
        return totalProjectsBudget.compareTo(currentBudget.getValue()) == -1
                || totalProjectsBudget.compareTo(currentBudget.getValue()) == 0;
    }

    private boolean statusIsAmarelo(Budget currentBudget, BigDecimal totalProjectsBudget) {
        return totalProjectsBudget.compareTo(currentBudget.getValue().multiply(new BigDecimal(1.1))) == -1
                || totalProjectsBudget.compareTo(currentBudget.getValue().multiply(new BigDecimal(1.1))) == 0;
    }

    private boolean statusIsVermelho(Budget currentBudget, BigDecimal totalProjectsBudget) {
        return totalProjectsBudget.compareTo(currentBudget.getValue().multiply(new BigDecimal(1.1))) == 1;
    }

    private BigDecimal calcTotalProjectsThatStartAndThatEndAtBudget(Budget budget, List<Project> projectsProcessed) {
        final List<Project> projectsThatStartAndThatEndAtBudget = projectRepository
                .findByDateStartBetweenAndDateFinalBetween(budget.getDateStart(), budget.getDateFinal(), budget.getDateStart(), budget.getDateFinal());
        return calcTotalProjects(budget, projectsThatStartAndThatEndAtBudget, PROJECTS_THAT_START_AND_THAT_END_AT_BUDGET, projectsProcessed);
    }

    private BigDecimal calcTotalProjectsThatEndAtBudget(Budget budget, List<Project> projectsProcessed) {
        final List<Project> projectsThatEndAtBudget = projectRepository.findByDateFinalBefore(budget.getDateFinal());
        return calcTotalProjects(budget, projectsThatEndAtBudget, PROJECTS_THAT_END_AT_BUDGET, projectsProcessed);
    }

    private BigDecimal calcTotalProjectsThatStartAtBudget(Budget budget, List<Project> projectsProcessed) {
        final List<Project> projectsThatStartAtBudget = projectRepository.findByDateStartAfter(budget.getDateStart());
        return calcTotalProjects(budget, projectsThatStartAtBudget, PROJECTS_THAT_START_AT_BUDGET, projectsProcessed);
    }

    private BigDecimal calcTotalProjectsThatStartAndEndOffBudget(Budget budget, List<Project> projectsProcessed) {
        final List<Project> projectsThatStartAndEndOffBudget = projectRepository
                .findByDateStartBeforeAndDateFinalAfter(budget.getDateStart(), budget.getDateFinal());
        return calcTotalProjects(budget, projectsThatStartAndEndOffBudget, PROJECTS_THAT_START_AT_BUDGET, projectsProcessed);
    }

    private BigDecimal calcTotalProjects(Budget budget, List<Project> projects, String typeProject, List<Project> projectsProcessed) {
        projects.removeAll(projectsProcessed);

        BigDecimal totalProjects = new BigDecimal(0);
        for (Project project : projects) {
            final long daysOfProject = ChronoUnit.DAYS.between(project.getDateStart(), project.getDateFinal());
            final long daysOfProjectAtBudget = calcDaysOfProjectAtBudget(budget, project, typeProject);

            final BigDecimal totalSalaryEmployees = calcTotalSalaryEmployees(project.getEmployeeList(), new BigDecimal(daysOfProjectAtBudget));
            totalProjects = totalProjects.add(project.getValue().divide(new BigDecimal(daysOfProject), RoundingMode.HALF_UP)
                    .multiply(new BigDecimal(daysOfProjectAtBudget)).add(totalSalaryEmployees));
        }

        projectsProcessed.addAll(projects);
        return totalProjects;
    }

    private long calcDaysOfProjectAtBudget(Budget budget, Project project, String typeProject) {

        switch (typeProject) {
            case PROJECTS_THAT_START_AND_THAT_END_AT_BUDGET:
                return ChronoUnit.DAYS.between(project.getDateStart(), project.getDateFinal());
            case PROJECTS_THAT_END_AT_BUDGET:
                return ChronoUnit.DAYS.between(budget.getDateStart(), project.getDateFinal());
            case PROJECTS_THAT_START_AT_BUDGET:
                return ChronoUnit.DAYS.between(project.getDateStart(), budget.getDateFinal());
            case PROJECTS_THAT_START_AND_THAT_END_OFF_BUDGET:
                return ChronoUnit.DAYS.between(budget.getDateStart(), budget.getDateFinal());
            default:
                return 0;
        }
    }

    private BigDecimal calcTotalSalaryEmployees(List<Employee> employees, BigDecimal daysOfProjectAtBudget) {
        BigDecimal totalSalaryEmployees = new BigDecimal(0);

        for (Employee employee : employees) {
            totalSalaryEmployees =
                    totalSalaryEmployees.add(employee.getSalary().divide(DAYS_OF_MONTH, RoundingMode.HALF_UP).multiply(daysOfProjectAtBudget));
        }

        return totalSalaryEmployees;
    }
}
