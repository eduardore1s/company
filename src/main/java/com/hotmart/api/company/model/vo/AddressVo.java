package com.hotmart.api.company.model.vo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AddressVo {

    private Long id;

    private String country;

    private String state;

    private String city;

    private String street;

    private String zipCode;
}
