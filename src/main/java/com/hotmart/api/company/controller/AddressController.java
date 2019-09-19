package com.hotmart.api.company.controller;

import com.hotmart.api.company.model.dto.AddressDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/address")
public class AddressController {

    @GetMapping
    public ResponseEntity<AddressDto> getAddress(){
        return null;
    }
}
