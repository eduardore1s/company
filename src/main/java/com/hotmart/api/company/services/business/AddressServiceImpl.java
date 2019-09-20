package com.hotmart.api.company.services.business;

import com.hotmart.api.company.model.dto.AddressDto;
import com.hotmart.api.company.model.entity.Address;
import com.hotmart.api.company.model.mapper.AddressMapper;
import com.hotmart.api.company.services.data.AddressDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AddressServiceImpl implements AddressService{

    @Autowired
    private AddressMapper addressMapper;

    @Autowired
    private AddressDataService addressDataService;

    @Override
    public List<AddressDto> findAll() {

        final List<Address> addressList = addressDataService.findAll();
        if (!addressList.isEmpty()){
            return addressList.stream().map(addressMapper::toAddressDto).collect(Collectors.toList());
        }
        return null;
    }

    @Override
    public AddressDto create(AddressDto addressDto) {
        final Address address = addressDataService.create(addressMapper.toAddress(addressDto));
        return addressMapper.toAddressDto(address);
    }

    @Override
    public AddressDto update(Long id, AddressDto addressDto) {
        final Optional<Address> addressOptional = addressDataService.findById(id);

        if (addressOptional.isPresent()){
            final Address address = addressMapper.toAddress(addressDto);
            address.setId(addressOptional.get().getId());
            addressDataService.create(address);

            addressDto.setId(addressOptional.get().getId());
            return addressDto;
        }

        return null;
    }

    @Override
    public boolean delete(Long id) {
        final Optional<Address> addressOptional = addressDataService.findById(id);

        if (addressOptional.isPresent()){
            addressDataService.delete(addressOptional.get());
            return true;
        }

        return false;
    }

    @Override
    public AddressDto findById(Long id) {

        final Optional<Address> address = addressDataService.findById(id);

        if (address.isPresent()){
            return addressMapper.toAddressDto(address.get());
        }

        return null;
    }


}
