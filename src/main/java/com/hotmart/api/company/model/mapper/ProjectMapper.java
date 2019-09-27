package com.hotmart.api.company.model.mapper;

import com.hotmart.api.company.model.form.ProjectForm;
import com.hotmart.api.company.model.vo.ProjectVo;
import com.hotmart.api.company.model.entity.Project;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface ProjectMapper {

    Project toProject(ProjectForm projectForm);

    ProjectVo toProjectDtoResponse(Project project);

    void updateProject(ProjectForm projectForm, @MappingTarget Project project);
}
