package com.hotmart.api.company.services;

import com.hotmart.api.company.model.dto.AddressDto;
import com.hotmart.api.company.model.entity.Address;

public interface AddressDataService {

    Address create(Address address);
    AddressDto toDto(Address address);

}
