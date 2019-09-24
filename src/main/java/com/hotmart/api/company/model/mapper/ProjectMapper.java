package com.hotmart.api.company.model.mapper;

import com.hotmart.api.company.model.dto.request.ProjectDtoRequest;
import com.hotmart.api.company.model.dto.response.ProjectDtoResponse;
import com.hotmart.api.company.model.entity.Project;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface ProjectMapper {

    Project toProject(ProjectDtoRequest projectDtoRequest);

    ProjectDtoResponse toProjectDtoResponse(Project project);

    void updateProject(ProjectDtoRequest projectDtoRequest, @MappingTarget Project project);
}
