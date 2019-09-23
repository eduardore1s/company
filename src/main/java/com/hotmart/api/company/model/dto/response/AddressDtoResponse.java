package com.hotmart.api.company.model.dto.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AddressDtoResponse {

    private Long id;

    private String country;

    private String state;

    private String city;

    private String street;

    private String zipCode;
}
