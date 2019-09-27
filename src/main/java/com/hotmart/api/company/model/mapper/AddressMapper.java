package com.hotmart.api.company.model.mapper;

import com.hotmart.api.company.controller.form.AddressForm;
import com.hotmart.api.company.controller.vo.AddressVo;
import com.hotmart.api.company.model.entity.Address;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface AddressMapper {

    Address toAddress(AddressForm addressForm);

    AddressVo toAddressVo(Address address);

    void updateAddress(AddressForm addressForm, @MappingTarget Address address);
}
