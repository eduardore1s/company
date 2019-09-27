package com.hotmart.api.company.data;

import com.hotmart.api.company.controller.form.AddressForm;
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


    public static AddressForm buildAddressDtoRequest(Long id){

        final AddressForm addressForm = new AddressForm();
        addressForm.setCity("CITY OF ID " + id.toString());
        addressForm.setCountry("COUNTRY OF ID " + id.toString());
        addressForm.setState("STATE OF ID ");
        addressForm.setStreet("STREET OF ID " + id.toString());
        addressForm.setZipCode("ZIP CODE OF ID " + id.toString());

        return addressForm;
    }




}
