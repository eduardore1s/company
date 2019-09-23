package com.hotmart.api.company.services.business;

import com.hotmart.api.company.model.dto.request.AddressDtoRequest;
import com.hotmart.api.company.model.dto.response.AddressDtoResponse;
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
    public List<AddressDtoResponse> findAll() {

        final List<Address> addressList = addressDataService.findAll();
        if (!addressList.isEmpty()){
            return addressList.stream().map(addressMapper::toAddressDtoResponse).collect(Collectors.toList());
        }
        return null;
    }

    @Override
    public AddressDtoResponse create(AddressDtoRequest addressDtoRequest) {
        final Address address = addressDataService.save(addressMapper.toAddress(addressDtoRequest));
        return addressMapper.toAddressDtoResponse(address);
    }

    @Override
    public AddressDtoResponse update(Long id, AddressDtoRequest addressDtoRequest) {
        final Optional<Address> addressOptional = addressDataService.findById(id);

        if (addressOptional.isPresent()){
            final Address address = addressOptional.get();
            addressMapper.updateAddress(addressDtoRequest, address);
            addressDataService.save(address);

            return addressMapper.toAddressDtoResponse(address);
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
    public AddressDtoResponse findById(Long id) {

        final Optional<Address> address = addressDataService.findById(id);

        if (address.isPresent()){
            return addressMapper.toAddressDtoResponse(address.get());
        }

        return null;
    }


}
