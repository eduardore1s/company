package com.hotmart.api.company.services.business;

import com.hotmart.api.company.model.dto.request.AddressDtoRequest;
import com.hotmart.api.company.model.dto.response.AddressDtoResponse;
import com.hotmart.api.company.model.entity.Address;
import com.hotmart.api.company.model.mapper.AddressMapper;
import com.hotmart.api.company.repository.AddressRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AddressService {

    private final AddressMapper addressMapper;

    private final AddressRepository addressRepository;

    public List<AddressDtoResponse> findAll() {

        final List<Address> addressList = addressRepository.findAll();
        if (!addressList.isEmpty()){
            return addressList.stream().map(addressMapper::toAddressDtoResponse).collect(Collectors.toList());
        }
        return null;
    }

    public AddressDtoResponse create(AddressDtoRequest addressDtoRequest) {
        final Address address = addressRepository.save(addressMapper.toAddress(addressDtoRequest));
        return addressMapper.toAddressDtoResponse(address);
    }

    public AddressDtoResponse update(Long id, AddressDtoRequest addressDtoRequest) {
        final Optional<Address> addressOptional = addressRepository.findById(id);

        if (addressOptional.isPresent()){
            final Address address = addressOptional.get();
            addressMapper.updateAddress(addressDtoRequest, address);
            addressRepository.save(address);

            return addressMapper.toAddressDtoResponse(address);
        }

        return null;
    }

    public boolean delete(Long id) {
        final Optional<Address> addressOptional = addressRepository.findById(id);

        if (addressOptional.isPresent()){
            addressRepository.delete(addressOptional.get());
            return true;
        }

        return false;
    }

    public AddressDtoResponse findById(Long id) {

        final Optional<Address> address = addressRepository.findById(id);

        if (address.isPresent()){
            return addressMapper.toAddressDtoResponse(address.get());
        }

        return null;
    }


}
