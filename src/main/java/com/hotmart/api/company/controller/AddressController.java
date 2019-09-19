package com.hotmart.api.company.controller;

import com.hotmart.api.company.model.entity.Address;
import com.hotmart.api.company.model.mapper.AddressMapper;
import com.hotmart.api.company.services.AddressDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/address")
public class AddressController {

    @Autowired
    private AddressMapper addressMapper;

    @Autowired
    private AddressDataService addressDataService;

    @GetMapping
    public ResponseEntity<?> getAddress(){
        final List<Address> addresses = addressDataService.findAll();
        if (addresses.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(addresses.stream().map(addressMapper::toAddressDto));
    }

//    @PostMapping
//    public ResponseEntity<?> postAddress(@RequestParam AddressDto addressDto){
//       return addressDataService.create(addressDto);
//    }
}
