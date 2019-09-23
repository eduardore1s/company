package com.hotmart.api.company.model.mapper;

import com.hotmart.api.company.model.dto.request.AddressDtoRequest;
import com.hotmart.api.company.model.dto.response.AddressDtoResponse;
import com.hotmart.api.company.model.entity.Address;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface AddressMapper {

    Address toAddress(AddressDtoRequest addressDtoRequest);

    AddressDtoResponse toAddressDtoResponse(Address address);

    void updateAddress(AddressDtoRequest addressDtoRequest, @MappingTarget Address address);
}
