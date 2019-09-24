package com.hotmart.api.company.controller;

import com.hotmart.api.company.model.dto.request.AddressDtoRequest;
import com.hotmart.api.company.model.dto.response.AddressDtoResponse;
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

        final List<AddressDtoResponse> addressDtoResponseList = addressService.findAll();

        if (addressDtoResponseList != null) {
            return ResponseEntity.ok(addressDtoResponseList);
        }
        return ResponseEntity.noContent().build();
    }

    @PostMapping
    public ResponseEntity<AddressDtoResponse> postAddress(@Valid @RequestBody AddressDtoRequest addressDtoRequest,
                                                  UriComponentsBuilder uriBuilder){

        final AddressDtoResponse addressDtoResponse = addressService.create(addressDtoRequest);

        if (addressDtoResponse != null){
            final URI uri = uriBuilder.path("api/v1/address/{id}").buildAndExpand(addressDtoResponse.getId()).toUri();
            return ResponseEntity.created(uri).body(addressDtoResponse);
        }
        return ResponseEntity.unprocessableEntity().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getAddress(@PathVariable Long id){

        final AddressDtoResponse addressDtoResponse = addressService.findById(id);

        if (addressDtoResponse != null) {
            return ResponseEntity.ok(addressDtoResponse);
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<AddressDtoResponse> putAddress(@PathVariable Long id,
                                                         @Valid @RequestBody AddressDtoRequest addressDtoRequest){

        final AddressDtoResponse addressDtoResponse = addressService.update(id, addressDtoRequest);

        if (addressDtoResponse != null){
            return new ResponseEntity<>(addressDtoResponse, HttpStatus.CREATED);
        }

        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<AddressDtoResponse> deleteAddress(@PathVariable Long id){

        if (addressService.delete(id)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
