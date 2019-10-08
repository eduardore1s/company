package com.hotmart.api.company.controller.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class TokenVo {

    private String token;

    private String type;
}
