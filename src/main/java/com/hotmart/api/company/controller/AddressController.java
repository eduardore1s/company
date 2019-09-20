package com.hotmart.api.company.controller;

import com.hotmart.api.company.model.dto.AddressDto;
import com.hotmart.api.company.services.business.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/v1/addresses")
public class AddressController {

    @Autowired
    private AddressService addressService;

    @GetMapping
    public ResponseEntity<?> getAddresses(){

        final List<AddressDto> addressDtoList = addressService.findAll();

        if (addressDtoList == null) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(addressDtoList);
    }

    @PostMapping
    public ResponseEntity<AddressDto> postAddress(@Valid @RequestBody AddressDto addressDto,
                                                  UriComponentsBuilder uriBuilder){

        final AddressDto addressDtoResponse = addressService.create(addressDto);

        final URI uri = uriBuilder.path("api/v1/address/{id}").buildAndExpand(addressDtoResponse.getId()).toUri();
        return ResponseEntity.created(uri).body(addressDtoResponse);
//        return new ResponseEntity<>(addressDtoResponse, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getAddress(@PathVariable Long id){

        final AddressDto addressDtoResponse = addressService.findById(id);

        if (addressDtoResponse == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(addressDtoResponse);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AddressDto> putAddress(@PathVariable Long id, @RequestBody AddressDto addressDto){

        final AddressDto addressDtoResponse = addressService.update(id, addressDto);

        if (addressDtoResponse == null){
            return ResponseEntity.notFound().build();
        }

        return new ResponseEntity<>(addressDtoResponse, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<AddressDto> deleteAddress(@PathVariable Long id){

        if (!addressService.delete(id)) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.noContent().build();
    }
}
