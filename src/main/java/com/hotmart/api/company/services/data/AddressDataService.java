package com.hotmart.api.company.services.data;

import com.hotmart.api.company.model.entity.Address;

import java.util.List;
import java.util.Optional;

public interface AddressDataService {

    List<Address> findAll();
    Address save(Address address);
    void delete(Address address);
    Optional<Address> findById(Long id);

}
