package com.hotmart.api.company.services.business;

import com.hotmart.api.company.controller.form.AddressForm;
import com.hotmart.api.company.controller.vo.AddressVo;
import com.hotmart.api.company.model.entity.Address;
import com.hotmart.api.company.model.exception.GenericErrorException;
import com.hotmart.api.company.model.exception.ResourceNotFoundException;
import com.hotmart.api.company.model.mapper.AddressMapper;
import com.hotmart.api.company.repository.AddressRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AddressService {

    private final AddressMapper addressMapper;

    private final AddressRepository addressRepository;

    public List<AddressVo> findAll() {

        final List<Address> addressList = addressRepository.findAll();
        if (!addressList.isEmpty()) {
            return addressList.stream().map(addressMapper::toAddressVo).collect(Collectors.toList());
        }
        return new ArrayList<>();
    }

    public AddressVo create(AddressForm addressForm) throws GenericErrorException {
        final Address address = addressRepository.save(addressMapper.toAddress(addressForm));

        if (address != null) {
            return addressMapper.toAddressVo(address);
        }
        throw new GenericErrorException(null, "Ocorreu um erro ao processar a criação deste recurso.");
    }

    public AddressVo update(Long id, AddressForm addressForm) throws ResourceNotFoundException {
        final Optional<Address> addressOptional = addressRepository.findById(id);

        if (addressOptional.isPresent()) {
            final Address address = addressOptional.get();
            addressMapper.updateAddress(addressForm, address);
            addressRepository.save(address);
            return addressMapper.toAddressVo(address);
        }
        throw new ResourceNotFoundException("id", "Nao existe Address para este id.");
    }

    public void delete(Long id) throws ResourceNotFoundException {
        final Optional<Address> addressOptional = addressRepository.findById(id);

        if (!addressOptional.isPresent()) {
            throw new ResourceNotFoundException("id", "Nao existe Address para este id.");
        }
        addressRepository.delete(addressOptional.get());
    }

    public AddressVo findById(Long id) throws ResourceNotFoundException {

        final Optional<Address> address = addressRepository.findById(id);

        if (address.isPresent()) {
            return addressMapper.toAddressVo(address.get());
        }
        throw new ResourceNotFoundException("id", "Nao existe Address para este id.");
    }


}
