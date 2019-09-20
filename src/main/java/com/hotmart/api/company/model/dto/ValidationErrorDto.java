package com.hotmart.api.company.model.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ValidationErrorDto {

    private String field;
    private String message;
}
