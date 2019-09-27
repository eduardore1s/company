package com.hotmart.api.company.model.mapper;

import com.hotmart.api.company.controller.form.ProjectForm;
import com.hotmart.api.company.controller.vo.ProjectVo;
import com.hotmart.api.company.model.entity.Project;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface ProjectMapper {

    Project toProject(ProjectForm projectForm);

    ProjectVo toProjectVo(Project project);

    void updateProject(ProjectForm projectForm, @MappingTarget Project project);
}
