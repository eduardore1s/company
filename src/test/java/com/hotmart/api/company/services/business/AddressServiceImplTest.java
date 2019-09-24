package com.hotmart.api.company.services.business;

import com.hotmart.api.company.data.AddressDataFactory;
import com.hotmart.api.company.model.dto.request.AddressDtoRequest;
import com.hotmart.api.company.model.dto.response.AddressDtoResponse;
import com.hotmart.api.company.model.entity.Address;
import com.hotmart.api.company.model.mapper.AddressMapper;
import com.hotmart.api.company.model.mapper.AddressMapperImpl;
import com.hotmart.api.company.services.data.AddressDataService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RunWith(MockitoJUnitRunner.class)
public class AddressServiceImplTest {

    private AddressServiceImpl addressServiceImpl;

    private AddressMapper addressMapper;

    @Mock
    private AddressDataService addressDataService;

    @Before
    public void init(){
        addressMapper = new AddressMapperImpl();
        addressServiceImpl = new AddressServiceImpl(addressMapper, addressDataService);
    }

    @Test
    public void findAllShouldReturnListAddressDtoResponseWith3Elements(){
        final List<Address> addressList = new ArrayList<>();
        addressList.add(AddressDataFactory.buildAddress(1L));
        addressList.add(AddressDataFactory.buildAddress(2L));
        addressList.add(AddressDataFactory.buildAddress(3L));
        Mockito.when(addressDataService.findAll()).thenReturn(addressList);

        final List<AddressDtoResponse> addressListDtoResponse = addressServiceImpl.findAll();

        Assert.assertTrue(addressListDtoResponse.size() == 3);
        Assert.assertEquals(addressList.get(0).getStreet(), addressListDtoResponse.get(0).getStreet());
        Assert.assertEquals(addressList.get(0).getCity(), addressListDtoResponse.get(0).getCity());
        Assert.assertEquals(addressList.get(0).getCountry(), addressListDtoResponse.get(0).getCountry());
        Assert.assertEquals(addressList.get(0).getState(), addressListDtoResponse.get(0).getState());
        Assert.assertEquals(addressList.get(0).getZipCode(), addressListDtoResponse.get(0).getZipCode());
        Assert.assertEquals(addressList.get(1).getStreet(), addressListDtoResponse.get(1).getStreet());
        Assert.assertEquals(addressList.get(2).getStreet(), addressListDtoResponse.get(2).getStreet());
    }

    @Test
    public void findAllShouldReturnNull(){
        Mockito.when(addressDataService.findAll()).thenReturn(new ArrayList<>());

        final List<AddressDtoResponse> addressListDtoResponse = addressServiceImpl.findAll();

        Assert.assertNull(addressListDtoResponse);
    }

    @Test
    public void createShouldReturnAddressDtoResponse(){
        final AddressDtoRequest addressDtoRequest = AddressDataFactory.buildAddressDtoRequest(1L);
        Mockito.when(addressDataService.save(Mockito.any())).thenReturn(addressMapper.toAddress(addressDtoRequest));

        final AddressDtoResponse addressDtoResponse = addressServiceImpl.create(addressDtoRequest);

        Assert.assertEquals(addressDtoRequest.getStreet(), addressDtoResponse.getStreet());
        Assert.assertEquals(addressDtoRequest.getCity(), addressDtoResponse.getCity());
        Assert.assertEquals(addressDtoRequest.getCountry(), addressDtoResponse.getCountry());
        Assert.assertEquals(addressDtoRequest.getState(), addressDtoResponse.getState());
        Assert.assertEquals(addressDtoRequest.getZipCode(), addressDtoResponse.getZipCode());
    }

    @Test
    public void updateShouldReturnAddressDtoResponse(){
        final Optional<Address> optionalAddress = Optional.of(AddressDataFactory.buildAddress(1L));
        Mockito.when(addressDataService.findById(1L)).thenReturn(optionalAddress);

        final AddressDtoRequest addressDtoRequest = AddressDataFactory.buildAddressDtoRequest(2L);

        final AddressDtoResponse addressDtoResponse = addressServiceImpl.update(1L, addressDtoRequest);

        Assert.assertEquals(addressDtoRequest.getStreet(), addressDtoResponse.getStreet());
        Assert.assertEquals(addressDtoRequest.getCity(), addressDtoResponse.getCity());
        Assert.assertEquals(addressDtoRequest.getCountry(), addressDtoResponse.getCountry());
        Assert.assertEquals(addressDtoRequest.getState(), addressDtoResponse.getState());
        Assert.assertEquals(addressDtoRequest.getZipCode(), addressDtoResponse.getZipCode());
    }

    @Test
    public void updateShouldReturnNull(){
        Mockito.when(addressDataService.findById(1L)).thenReturn(Optional.empty());

        final AddressDtoResponse addressDtoResponse = addressServiceImpl.update(1L, null);

        Assert.assertNull(addressDtoResponse);
    }

    @Test
    public void deleteShouldReturnTrue(){
        Mockito.when(addressDataService.findById(1L)).thenReturn(Optional.of(new Address()));

        Assert.assertTrue(addressServiceImpl.delete(1L));
    }


    @Test
    public void deleteShouldReturnFalse(){
        Mockito.when(addressDataService.findById(1L)).thenReturn(Optional.empty());

        Assert.assertFalse(addressServiceImpl.delete(1L));
    }

    @Test
    public void findByIdShouldReturnAddressDtoResponse(){
        final Address address = AddressDataFactory.buildAddress(1L);
        Mockito.when(addressDataService.findById(1L)).thenReturn(Optional.of(address));

        final AddressDtoResponse addressDtoResponse = addressServiceImpl.findById(1L);

        Assert.assertEquals(address.getStreet(), addressDtoResponse.getStreet());
        Assert.assertEquals(address.getZipCode(), addressDtoResponse.getZipCode());
        Assert.assertEquals(address.getState(), addressDtoResponse.getState());
        Assert.assertEquals(address.getCountry(), addressDtoResponse.getCountry());
        Assert.assertEquals(address.getCity(), addressDtoResponse.getCity());
    }

    @Test
    public void findByIdShouldReturnNull(){
        Mockito.when(addressDataService.findById(1L)).thenReturn(Optional.empty());

        Assert.assertNull(addressServiceImpl.findById(1L));
    }


}
