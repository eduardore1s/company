package com.hotmart.api.company.controller;

import com.hotmart.api.company.model.form.AddressForm;
import com.hotmart.api.company.model.vo.AddressVo;
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

        final List<AddressVo> addressVoList = addressService.findAll();

        if (addressVoList != null) {
            return ResponseEntity.ok(addressVoList);
        }
        return ResponseEntity.noContent().build();
    }

    @PostMapping
    public ResponseEntity<AddressVo> postAddress(@Valid @RequestBody AddressForm addressForm,
                                                 UriComponentsBuilder uriBuilder){

        final AddressVo addressVo = addressService.create(addressForm);

        if (addressVo != null){
            final URI uri = uriBuilder.path("api/v1/address/{id}").buildAndExpand(addressVo.getId()).toUri();
            return ResponseEntity.created(uri).body(addressVo);
        }
        return ResponseEntity.unprocessableEntity().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getAddress(@PathVariable Long id){

        final AddressVo addressVo = addressService.findById(id);

        if (addressVo != null) {
            return ResponseEntity.ok(addressVo);
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<AddressVo> putAddress(@PathVariable Long id,
                                                @Valid @RequestBody AddressForm addressForm){

        final AddressVo addressVo = addressService.update(id, addressForm);

        if (addressVo != null){
            return new ResponseEntity<>(addressVo, HttpStatus.CREATED);
        }

        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<AddressVo> deleteAddress(@PathVariable Long id){

        if (addressService.delete(id)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
