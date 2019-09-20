package com.hotmart.api.company.model.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AddressDto {

    private Long id;

    private String country;

    private String state;

    private String city;

    private String street;

    private String zipCode;
}
