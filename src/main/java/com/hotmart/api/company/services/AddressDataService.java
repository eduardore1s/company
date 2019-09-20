package com.hotmart.api.company.services;

import com.hotmart.api.company.model.dto.AddressDto;
import com.hotmart.api.company.model.entity.Address;

import java.util.List;
import java.util.Optional;

public interface AddressDataService {

    List<AddressDto> findAll();
    AddressDto create(AddressDto addressDto);
    AddressDto update(Long id, AddressDto addressDto);
    boolean delete(Long id);
    Optional<Address> findById(Long id);

}
