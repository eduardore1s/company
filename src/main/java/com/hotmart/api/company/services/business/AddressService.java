package com.hotmart.api.company.services.business;

import com.hotmart.api.company.controller.form.AddressForm;
import com.hotmart.api.company.controller.vo.AddressVo;
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

    public List<AddressVo> findAll() {

        final List<Address> addressList = addressRepository.findAll();
        if (!addressList.isEmpty()){
            return addressList.stream().map(addressMapper::toAddressDtoResponse).collect(Collectors.toList());
        }
       return null;// new ArrayList<>();
    }

    public AddressVo create(AddressForm addressForm) {
        final Address address = addressRepository.save(addressMapper.toAddress(addressForm));

        //if (address != null){
            return addressMapper.toAddressDtoResponse(address);
//        }
//
//        return null; //throws exception de erro;

    }

    public AddressVo update(Long id, AddressForm addressForm) {
        final Optional<Address> addressOptional = addressRepository.findById(id);

        if (addressOptional.isPresent()){
            final Address address = addressOptional.get();
            addressMapper.updateAddress(addressForm, address);
            addressRepository.save(address);

            return addressMapper.toAddressDtoResponse(address);
        }

        return null; //throws expection de not found
    }

    public boolean delete(Long id) {
        final Optional<Address> addressOptional = addressRepository.findById(id);

        if (addressOptional.isPresent()){
            addressRepository.delete(addressOptional.get());
            return true;
        }

        return false; //throws expection de not found
    }

    public AddressVo findById(Long id) {

        final Optional<Address> address = addressRepository.findById(id);

        if (address.isPresent()){
            return addressMapper.toAddressDtoResponse(address.get());
        }

        return null; //throws expection de not found
    }


}
