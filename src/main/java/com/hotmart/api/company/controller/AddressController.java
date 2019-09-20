package com.hotmart.api.company.controller;

import com.hotmart.api.company.model.dto.AddressDto;
import com.hotmart.api.company.services.AddressDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/address")
public class AddressController {

    @Autowired
    private AddressDataService addressDataService;

    @GetMapping
    public ResponseEntity<?> getAddresses(){

        final List<AddressDto> addressDtoList = addressDataService.findAll();

        if (addressDtoList == null) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(addressDtoList);
    }

    @PostMapping
    public ResponseEntity<AddressDto> postAddress(@Valid @RequestBody AddressDto addressDto){

        final AddressDto addressDtoResponse = addressDataService.create(addressDto);
        return new ResponseEntity<>(addressDtoResponse, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getAddress(@PathVariable Long id){

        final AddressDto addressDtoResponse = addressDataService.findById(id);

        if (addressDtoResponse == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(addressDtoResponse);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AddressDto> putAddress(@PathVariable Long id, @RequestBody AddressDto addressDto){

        final AddressDto addressDtoResponse = addressDataService.update(id, addressDto);

        if (addressDtoResponse == null){
            return ResponseEntity.notFound().build();
        }

        return new ResponseEntity<>(addressDtoResponse, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<AddressDto> deleteAddress(@PathVariable Long id){

        if (!addressDataService.delete(id)) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.noContent().build();
    }
}
