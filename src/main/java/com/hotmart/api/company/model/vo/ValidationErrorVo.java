package com.hotmart.api.company.model.vo;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ValidationErrorVo {

    private String field;
    private String message;
}
