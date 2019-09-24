package com.hotmart.api.company.data;

import com.hotmart.api.company.model.dto.request.AddressDtoRequest;
import com.hotmart.api.company.model.entity.Address;

public class AddressDataFactory {

    public static Address buildAddress(Long id){

        final Address address = new Address();
        address.setId(id);
        address.setCity("CITY OF ID " + id.toString());
        address.setCountry("COUNTRY OF ID " + id.toString());
        address.setState("STATE OF ID ");
        address.setStreet("STREET OF ID " + id.toString());
        address.setZipCode("ZIP CODE OF ID " + id.toString());

        return address;
    }


    public static AddressDtoRequest buildAddressDtoRequest(Long id){

        final AddressDtoRequest addressDtoRequest = new AddressDtoRequest();
        addressDtoRequest.setCity("CITY OF ID " + id.toString());
        addressDtoRequest.setCountry("COUNTRY OF ID " + id.toString());
        addressDtoRequest.setState("STATE OF ID ");
        addressDtoRequest.setStreet("STREET OF ID " + id.toString());
        addressDtoRequest.setZipCode("ZIP CODE OF ID " + id.toString());

        return addressDtoRequest;
    }




}
