package com.hotmart.api.company.model.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
public class AddressDto {

    private Long id;

    @NotNull
    private String country;

    @NotNull
    private String state;

    @NotNull
    private String city;

    @NotNull
    private String street;

    @NotNull
    private String zipCode;
}
