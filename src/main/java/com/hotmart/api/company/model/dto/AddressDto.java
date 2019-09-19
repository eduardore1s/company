package com.hotmart.api.company.model.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class AddressDto {

    private String country;

    private String state;

    private String city;

    private String street;

    private String zipCode;
}
