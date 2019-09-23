package com.hotmart.api.company.services.business;

import com.hotmart.api.company.model.dto.request.AddressDtoRequest;
import com.hotmart.api.company.model.dto.response.AddressDtoResponse;

import java.util.List;

public interface AddressService {

    List<AddressDtoResponse> findAll();
    AddressDtoResponse create(AddressDtoRequest addressDtoRequest);
    AddressDtoResponse update(Long id, AddressDtoRequest addressDtoRequest);
    boolean delete(Long id);
    AddressDtoResponse findById(Long id);

}
