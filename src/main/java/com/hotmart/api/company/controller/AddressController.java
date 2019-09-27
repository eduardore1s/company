package com.hotmart.api.company.controller;

import com.hotmart.api.company.controller.form.AddressForm;
import com.hotmart.api.company.controller.vo.AddressVo;
import com.hotmart.api.company.services.business.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/api/v1/addresses")
public class AddressController {

    @Autowired
    private AddressService addressService;

    @GetMapping
    public ResponseEntity<?> getAddresses(){
        return ResponseEntity.ok(addressService.findAll());
    }

    @PostMapping
    public ResponseEntity<AddressVo> postAddress(@Valid @RequestBody AddressForm addressForm,
                                                 UriComponentsBuilder uriBuilder) {
        final AddressVo addressVo = addressService.create(addressForm);
        final URI uri = uriBuilder.path("api/v1/address/{id}").buildAndExpand(addressVo.getId()).toUri();
        return ResponseEntity.created(uri).body(addressVo);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getAddress(@PathVariable Long id) {
        return ResponseEntity.ok(addressService.findById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<AddressVo> putAddress(@PathVariable Long id, @Valid @RequestBody AddressForm addressForm) {
        final AddressVo addressVo = addressService.update(id, addressForm);
        return new ResponseEntity<>(addressVo, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<AddressVo> deleteAddress(@PathVariable Long id) {
        addressService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
