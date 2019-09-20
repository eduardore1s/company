package com.hotmart.api.company.services;

import com.hotmart.api.company.model.dto.AddressDto;

import java.util.List;

public interface AddressDataService {

    List<AddressDto> findAll();
    AddressDto create(AddressDto addressDto);
    AddressDto update(Long id, AddressDto addressDto);
    boolean delete(Long id);
    AddressDto findById(Long id);

}
