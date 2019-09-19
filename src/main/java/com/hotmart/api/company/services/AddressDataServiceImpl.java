package com.hotmart.api.company.services;

import com.hotmart.api.company.model.entity.Address;
import com.hotmart.api.company.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressDataServiceImpl implements AddressDataService {

    @Autowired
    private AddressRepository addressRepository;

    @Override
    public List<Address> findAll() {
        return addressRepository.findAll();
    }

    @Override
    public Address create(Address address) {
        return addressRepository.save(address);
    }

}
