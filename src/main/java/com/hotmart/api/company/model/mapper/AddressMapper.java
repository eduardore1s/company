package com.hotmart.api.company.model.mapper;

import com.hotmart.api.company.model.dto.AddressDto;
import com.hotmart.api.company.model.entity.Address;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AddressMapper {

    Address toAddress(AddressDto addressDto);

    AddressDto toAddressDto(Address address);
}
