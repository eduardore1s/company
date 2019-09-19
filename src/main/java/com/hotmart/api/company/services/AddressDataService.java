package com.hotmart.api.company.services;

import com.hotmart.api.company.model.entity.Address;

import java.util.List;

public interface AddressDataService {

    List<Address> findAll();
    Address create(Address address);

}
