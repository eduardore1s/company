package com.hotmart.api.company.services;

import com.hotmart.api.company.model.dto.AddressDto;
import com.hotmart.api.company.model.entity.Address;
import com.hotmart.api.company.model.mapper.AddressMapper;
import com.hotmart.api.company.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AddressDataServiceImpl implements AddressDataService {

    @Autowired
    private AddressMapper addressMapper;

    @Autowired
    private AddressRepository addressRepository;

    @Override
    public List<AddressDto> findAll() {

        final List<Address> addressList = addressRepository.findAll();
        if (addressList.isEmpty()){
            return null;
        }
        return addressList.stream().map(addressMapper::toAddressDto).collect(Collectors.toList());
    }

    @Override
    public AddressDto create(AddressDto addressDto) {
        final Address address = addressRepository.save(addressMapper.toAddress(addressDto));
        return addressMapper.toAddressDto(address);
    }

    @Override
    public AddressDto update(Long id, AddressDto addressDto) {
        final Optional<Address> addressOptional = addressRepository.findById(id);

        if (!addressOptional.isPresent()){
            return null;
        }

        final Address address = addressMapper.toAddress(addressDto);
        address.setId(addressOptional.get().getId());
        addressRepository.save(address);

        addressDto.setId(addressOptional.get().getId());
        return addressDto;
    }

    @Override
    public boolean delete(Long id) {
        final Optional<Address> addressOptional = addressRepository.findById(id);

        if (!addressOptional.isPresent()){
            return false;
        }

        addressRepository.delete(addressOptional.get());
        return true;
    }

    @Override
    public AddressDto findById(Long id) {

        final Optional<Address> address = addressRepository.findById(id);

        if (!address.isPresent()){
            return null;
        }

        return addressMapper.toAddressDto(address.get());
    }

}
