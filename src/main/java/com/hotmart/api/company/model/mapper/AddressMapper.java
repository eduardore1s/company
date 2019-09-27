package com.hotmart.api.company.model.mapper;

import com.hotmart.api.company.model.form.AddressForm;
import com.hotmart.api.company.model.vo.AddressVo;
import com.hotmart.api.company.model.entity.Address;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface AddressMapper {

    Address toAddress(AddressForm addressForm);

    AddressVo toAddressDtoResponse(Address address);

    void updateAddress(AddressForm addressForm, @MappingTarget Address address);
}
