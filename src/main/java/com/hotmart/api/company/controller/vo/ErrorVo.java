package com.hotmart.api.company.controller.vo;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ErrorVo {

    private String field;
    private String message;
}
